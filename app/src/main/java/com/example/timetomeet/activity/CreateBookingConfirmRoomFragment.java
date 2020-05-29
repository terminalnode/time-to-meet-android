package com.example.timetomeet.activity;

import android.os.Bundle;
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
import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.entity.AvailableRoom;
import com.example.timetomeet.retrofit.entity.ConferenceRoom;
import com.example.timetomeet.retrofit.entity.Seating;

public class CreateBookingConfirmRoomFragment extends Fragment {
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

    view.findViewById(R.id.confirmRoomButton)
        .setOnClickListener(this::confirmRoomButtonClicked);
  }

  private void confirmRoomButtonClicked(View view) {
    Bundle bookingBundle = ((CreateBookingActivity) getActivity()).getBookingBundle();
    AvailableRoom selectedRoom = bookingBundle.getParcelable(Helper.BUNDLE_SELECTED_ROOM);
    ConferenceRoom conferenceRoom = selectedRoom.getAssociatedConferenceRoom();
    ProgressBar progressBar = view.findViewById(R.id.progressBar);

    // Check if user wants activity info
    RadioGroup wantActivityInfoRadioGroup = view.findViewById(R.id.wantActivityInfoRadioGroup);
    boolean wantActivityInfo = wantActivityInfoRadioGroup
        .getCheckedRadioButtonId() == R.id.yesActivityInfoRadioButton;

    // Check if user wants room info
    RadioGroup wantRoomInfoRadioGroup = view.findViewById(R.id.wantRoomInfoRadioGroup);
    boolean wantRoomInfo = wantRoomInfoRadioGroup
        .getCheckedRadioButtonId() == R.id.yesRoomInfoRadioButton;

    // Check which time slots the user wants to book.
    // id31 is the before noon time slot.
    // id32 is the afternoon time slot.
    RadioGroup timeSlotRadioGroup = view.findViewById(R.id.timeSlotRadioGroup);
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

    // Get special requests
    EditText specialRequestMultiLineText = view.findViewById(R.id.specialRequestMultilineText);
    String specialRequest = specialRequestMultiLineText.getText().toString();

    Spinner paymentAlternativeSpinner = view.findViewById(R.id.paymentAlternativeSpinner);
    Spinner seatingChoiceSpinner = view.findViewById(R.id.seatingChoiceSpinner);
    //Seating seating = (Seating) seatingChoiceSpinner.getSelectedItem();
  }
}
