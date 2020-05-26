package com.example.timetomeet.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.timetomeet.Helper;
import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.entity.AvailableRoom;
import com.example.timetomeet.retrofit.entity.Venue;

public class CreateBookingAvailableRoomFragment extends Fragment {

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_create_booking_available_room, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    TextView venueNameTextView = view.findViewById(R.id.venueNameTextView);
    TextView cityNameTextView = view.findViewById(R.id.cityNameTextView);
    TextView beforeNoonPriceTextView = view.findViewById(R.id.beforeNoonPriceTextView);
    TextView afterNoonPriceTextView = view.findViewById(R.id.afterNoonPriceTextView);
    TextView fullDayPriceTextView = view.findViewById(R.id.fullDayPriceTextView);
    TextView amOpeningHoursTimeTextView = view.findViewById(R.id.amOpeningHoursTimeTextView);
    TextView pmOpeningHoursTimeTextView = view.findViewById(R.id.pmOpeningHoursTimeTextView);

    Bundle bookingBundle = ((CreateBookingActivity) getActivity()).getBookingBundle();
    AvailableRoom selectedRoom = bookingBundle.getParcelable(Helper.BUNDLE_SELECTED_ROOM);

    String locale = Helper.getLocale();
    venueNameTextView.setText(Helper.getLocalizedName(selectedRoom.getAssociatedVenue(), locale));
    cityNameTextView.setText(Helper.getLocalizedName(selectedRoom.getAssociatedCity(), locale));
    beforeNoonPriceTextView.setText(String.format("%.02f kr", selectedRoom.getPreNoonPrice()));
    afterNoonPriceTextView.setText(String.format("%.02f kr", selectedRoom.getAfterNoonPrice()));
    fullDayPriceTextView.setText(String.format("%.02f kr", selectedRoom.getFullDayPrice()));
  }
}
