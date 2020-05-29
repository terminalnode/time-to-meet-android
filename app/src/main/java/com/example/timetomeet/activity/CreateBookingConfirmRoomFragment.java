package com.example.timetomeet.activity;

import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateBookingConfirmRoomFragment extends Fragment {
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
  String token;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState
  ) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_create_booking_confirm_room, container, false);
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
    EditText agreementNumber = view.findViewById(R.id.agreementNumberEditText);
    agreementNumber.setFilters(new InputFilter[] {
        new InputFilter.AllCaps(),
        new AlphaNumericFilter()
    });

    // Set on confirm room button-click
    view.findViewById(R.id.confirmRoomButton)
        .setOnClickListener(this::confirmRoomButtonClicked);
  }

  private void confirmRoomButtonClicked(View view) {
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
    createNewBooking(addBooking);

  }

  private void createNewBooking(BookingAdd addBooking) {
    RetrofitHelper
        .addBooking(addBooking, token)
        .enqueue(new Callback<JSONObject>() {
          @Override
          public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.body() != null) {
              Log.i(Logging.CreateBookingActivity, "Successfully started a booking");
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
          public void onFailure(Call<JSONObject> call, Throwable t) {
            Log.e(Logging.CreateBookingActivity, "Error on createNewBooking: " + t);
            Snackbar.make(getView(), R.string.something_went_wrong, Snackbar.LENGTH_LONG).show();
          }
        });
  }

  private void addTimeSlotToBooking() {
    RetrofitHelper.
  }
}
