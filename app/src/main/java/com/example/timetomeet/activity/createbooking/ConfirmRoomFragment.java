package com.example.timetomeet.activity.createbooking;

import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.timetomeet.Helper;
import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.example.timetomeet.customview.ParticipantsNumberTextWatcher;
import com.example.timetomeet.customview.adapters.AlphaNumericFilter;
import com.example.timetomeet.customview.adapters.PaymentAlternativeSpinnerAdapter;
import com.example.timetomeet.customview.adapters.SeatingSpinnerAdapter;
import com.example.timetomeet.retrofit.RetrofitHelper;
import com.example.timetomeet.retrofit.entity.AvailableRoom;
import com.example.timetomeet.retrofit.entity.BookingAdd;
import com.example.timetomeet.retrofit.entity.ConferenceRoomSeating;
import com.example.timetomeet.retrofit.entity.PaymentAlternative;
import com.example.timetomeet.retrofit.entity.TimeSlotAdd;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmRoomFragment extends Fragment {
  AvailableRoom selectedRoom;
  EditText specialRequestMultiLineText;
  EditText numberOfParticipantsEditText;
  EditText agreementNumberEditText;
  Spinner paymentAlternativeSpinner;
  Spinner seatingChoiceSpinner;
  RadioGroup timeSlotRadioGroup;
  RadioGroup wantRoomInfoRadioGroup;
  RadioGroup wantActivityInfoRadioGroup;
  ProgressBar progressBar;
  Button confirmRoomButton;
  String token;
  Runnable timeSlotChecker;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState
  ) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_confirm_room, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Bundle bookingBundle = ((CreateBookingActivity) getActivity()).getBookingBundle();
    Bundle apiData = getActivity().getIntent().getExtras();
    CreateBookingActivity activity = (CreateBookingActivity) getActivity();
    selectedRoom = bookingBundle.getParcelable(Helper.BUNDLE_SELECTED_ROOM);

    // Find views
    specialRequestMultiLineText = view.findViewById(R.id.specialRequestMultilineText);
    numberOfParticipantsEditText = view.findViewById(R.id.numberOfParticipantsEditText);
    paymentAlternativeSpinner = view.findViewById(R.id.paymentAlternativeSpinner);
    seatingChoiceSpinner = view.findViewById(R.id.seatingChoiceSpinner);
    timeSlotRadioGroup = view.findViewById(R.id.timeSlotRadioGroup);
    wantRoomInfoRadioGroup = view.findViewById(R.id.wantRoomInfoRadioGroup);
    wantActivityInfoRadioGroup = view.findViewById(R.id.wantActivityInfoRadioGroup);
    progressBar = view.findViewById(R.id.progressBar);
    agreementNumberEditText = view.findViewById(R.id.agreementNumberEditText);
    confirmRoomButton = view.findViewById(R.id.confirmRoomButton);

    // Find am/pm/full day radio buttons, disable the ones that are unavailable.
    RadioButton amRadioButton = view.findViewById(R.id.amRadioButton);
    RadioButton pmRadioButton = view.findViewById(R.id.pmRadioButton);
    RadioButton fullDayRadioButton = view.findViewById(R.id.fullDayRadioButton);

    if (selectedRoom.getId31() == null) {
      amRadioButton.setClickable(false);
      amRadioButton.setAlpha(0.5F);

      fullDayRadioButton.setClickable(false);
      fullDayRadioButton.setAlpha(0.5F);

      pmRadioButton.toggle();
    } else if (selectedRoom.getId32() == null) {
      pmRadioButton.setClickable(false);
      pmRadioButton.setAlpha(0.5F);

      fullDayRadioButton.setClickable(false);
      fullDayRadioButton.setAlpha(0.5F);
    }

    // Set up payment alternative spinner
    PaymentAlternativeSpinnerAdapter paymentAdapter = new PaymentAlternativeSpinnerAdapter(
        getContext(),
        apiData.getParcelableArrayList(Helper.BUNDLE_PAYMENT_ALTERNATIVES));
    paymentAlternativeSpinner.setAdapter(paymentAdapter);

    // Set up seating spinner
    SeatingSpinnerAdapter seatingAdapter = new SeatingSpinnerAdapter(
        getContext(),
        selectedRoom.getAssociatedConferenceRoom().getDefaultSeating(),
        activity.getSeatingMap(),
        numberOfParticipantsEditText
    );
    seatingChoiceSpinner.setAdapter(seatingAdapter);

    // Make sure the user doesn't input anything stupid
    numberOfParticipantsEditText.addTextChangedListener(
        new ParticipantsNumberTextWatcher(seatingChoiceSpinner));

    // Set input filters to agreement number
    agreementNumberEditText.setFilters(new InputFilter[] {
        new InputFilter.AllCaps(),
        new AlphaNumericFilter()
    });

    // Set on confirm room button-click
    confirmRoomButton
        .setOnClickListener(this::confirmRoomButtonClicked);
  }

  private void confirmRoomButtonClicked(View view) {
    // Disable the confirm room button and show progress bar
    progressBar.setVisibility(View.VISIBLE);
    confirmRoomButton.setClickable(false);
    confirmRoomButton.setAlpha(0.5F);

    // Collect information from the form
    // 1. Get payment method
    PaymentAlternative payment = (PaymentAlternative) paymentAlternativeSpinner.getSelectedItem();

    // 2. Check if user wants room info
    boolean wantRoomInfo = wantRoomInfoRadioGroup
        .getCheckedRadioButtonId() == R.id.yesRoomInfoRadioButton;

    // 3. Check if user wants activity info
    boolean wantActivityInfo = wantActivityInfoRadioGroup
        .getCheckedRadioButtonId() == R.id.yesActivityInfoRadioButton;

    // 4. Check for special request
    String specialRequest = specialRequestMultiLineText.getText().toString();

    // 5. Get desired seating
    ConferenceRoomSeating seating = (ConferenceRoomSeating) seatingChoiceSpinner.getSelectedItem();

    // 6. Get number of participants
    int numberOfParticipants;
    try {
      numberOfParticipants = Integer.parseInt(numberOfParticipantsEditText.getText().toString());
    } catch (NumberFormatException e) {
      Log.e(Logging.CreateBookingActivity, "Could not parse number of participants as int");
      Snackbar.make(
          view,
          R.string.invalid_number_of_participants,
          Snackbar.LENGTH_LONG
      ).show();
      return;
    }

    // 7. Get agreement number
    String agreementNumber = agreementNumberEditText.getText().toString().trim();

    // 8. Get time slot IDs the user wants to book.
    // id31 is the before noon time slot.
    // id32 is the afternoon time slot.
    long[] listOfTimeSlots;
    switch (timeSlotRadioGroup.getCheckedRadioButtonId()) {
      case R.id.amRadioButton:
        listOfTimeSlots = new long[]{ selectedRoom.getId31() };
        break;

      case R.id.pmRadioButton:
        listOfTimeSlots = new long[]{ selectedRoom.getId32() };
        break;

      default:
        listOfTimeSlots = new long[]{ selectedRoom.getId31(), selectedRoom.getId32() };
    }

    // Create new booking
    BookingAdd addBooking = new BookingAdd();
    addBooking.setPaymentAlternative(payment.getId());
    addBooking.setWantHotelRoomInfo(wantRoomInfo);
    addBooking.setWantActivityInfo(wantActivityInfo);
    addBooking.setSpecialRequest(specialRequest);
    addBooking.setNumberOfParticipants(numberOfParticipants);
    addBooking.setBookingSourceSystem(2);
    addBooking.setAgreementNumber(agreementNumber);


    token = getActivity().getIntent().getStringExtra(Helper.BUNDLE_TOKEN);

    //Api calls
    createNewBooking(addBooking, listOfTimeSlots, seating);

  }

  private void createNewBooking(
      BookingAdd addBooking,
      long[] listOfTimeSlots,
      ConferenceRoomSeating seating
  ) {
    RetrofitHelper
        .addBooking(addBooking, token)
        .enqueue(new Callback<BookingAdd>() {
          @Override
          public void onResponse(Call<BookingAdd> call, Response<BookingAdd> response) {
            if (response.body() != null) {
              Log.i(Logging.CreateBookingActivity, "Successfully started a booking");

              addAllTimeSlots(listOfTimeSlots, seating);
            } else {
              Snackbar.make(getView(), R.string.something_went_wrong, Snackbar.LENGTH_LONG).show();
              try {
                Log.e(
                    Logging.CreateBookingActivity,
                    "Booking failed " + response.errorBody().string());
              } catch (IOException ignored) { }
            }
          }

          @Override
          public void onFailure(Call<BookingAdd> call, Throwable t) {
            Log.e(Logging.CreateBookingActivity, "Error on createNewBooking: " + t);
            Snackbar.make(getView(), R.string.something_went_wrong, Snackbar.LENGTH_LONG).show();
          }
        });
  }

  private void addAllTimeSlots(long[] listOfTimeSlots, ConferenceRoomSeating seating) {
    // Make the progress bar determinate again and set appropriate max
    int numberOfTimeSlots = listOfTimeSlots.length;
    List<TimeSlotAdd> finishedTimeSlots = new ArrayList<>();

    // Start adding the time slots
    for (long timeSlotId : listOfTimeSlots) {
      TimeSlotAdd timeSlotAdd = new TimeSlotAdd();
      timeSlotAdd.setChosenSeating(seating.getSeatingId());
      addTimeSlotToBooking(timeSlotId, timeSlotAdd, finishedTimeSlots);
    }

    // Runnable for checking that the time slots have been added
    long delayMillis = 250;
    Handler timeSlotCheckerHandler = new Handler();
    timeSlotChecker = () -> {
      boolean allTimeSlotsChecked = finishedTimeSlots.size() == numberOfTimeSlots;

      Log.i(
          Logging.CreateBookingActivity,
          String.format(
              "%s / %s time slots added, all time slots added: %s",
              finishedTimeSlots.size(),
              numberOfTimeSlots,
              allTimeSlotsChecked)
      );

      // Check if all time slots are done, and if so move to the next fragment
      if (allTimeSlotsChecked) {
        Log.i(Logging.CreateBookingActivity, "All time slots added!");
        progressBar.setVisibility(View.GONE);
        // TODO Send time slots to activity/next fragment, booking bundle or whatever.
        Log.i(Logging.CreateBookingActivity, "Moving over to food fragment");
        NavHostFragment
            .findNavController(ConfirmRoomFragment.this)
            .navigate(R.id.action_ConfirmRoomFragment_to_FoodFragment);

      } else {
        timeSlotCheckerHandler.postDelayed(timeSlotChecker, delayMillis);
      }
    };

    // Add runnable to handler
    timeSlotCheckerHandler.postDelayed(timeSlotChecker, delayMillis);
  }

  private void addTimeSlotToBooking(
      long timeSlotId,
      TimeSlotAdd timeSlotAdd,
      List<TimeSlotAdd> finishedTimeSlots
  ) {
    // TODO Add some kind of error handling if adding time slot fails

    RetrofitHelper
        .addTimeSlot(timeSlotAdd, timeSlotId, token)
        .enqueue(new Callback<TimeSlotAdd>() {
          @Override
          public void onResponse(Call<TimeSlotAdd> call, Response<TimeSlotAdd> response) {
            // Log the error and return if response body is null
            // Increment progress bar until we've implemented
            // some kind of proper error handling.
            if (response.body() == null) {
              try {
                Log.i("YOLO", response.errorBody().string());
              } catch (IOException ignored) { }
              finishedTimeSlots.add(null);
              return;
            }

            // Response body is not null, we have a time slot reserved \o/
            finishedTimeSlots.add(response.body());
            Log.i(
                Logging.CreateBookingActivity,
                "Successfully reserved time slot: " + response.body()
            );
          }

          @Override
          public void onFailure(Call<TimeSlotAdd> call, Throwable t) {
            Log.e(Logging.CreateBookingActivity, "Failed to add time slot: " + t);
            finishedTimeSlots.add(null);
          }
        });
  }
}
