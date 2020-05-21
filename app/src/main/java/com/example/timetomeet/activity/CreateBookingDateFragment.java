package com.example.timetomeet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.timetomeet.Helper;
import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.example.timetomeet.customview.CitySimplifiedSpinnerAdapter;
import com.example.timetomeet.customview.DateDisplayListener;
import com.example.timetomeet.retrofit.entity.CitySimplified;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This fragment is used to select a date or time span in which
 * to search for available rooms.
 */
public class CreateBookingDateFragment extends Fragment {
  private ConstraintLayout startDateDisplay, endDateDisplay;
  private Spinner spinCity;
  private List<CitySimplified> citiesWithVenues;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState
  ) {
    Intent intent = Objects.requireNonNull(getActivity()).getIntent();
    if (intent != null) {
      citiesWithVenues = intent.getParcelableArrayListExtra(Helper.BUNDLE_CITIES);
    } else {
      citiesWithVenues = new ArrayList<>();
    }

    Log.i(Logging.CreateBookingActivity, "Inflating CreateBookingDateFragment");
    return inflater.inflate(R.layout.fragment_create_booking_date, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    // Set up date labels
    startDateDisplay = view.findViewById(R.id.startDateDisplay);
    endDateDisplay = view.findViewById(R.id.endDateDisplay);
    TextView startDateText = startDateDisplay.findViewById(R.id.dateTextView);
    TextView endDateText = endDateDisplay.findViewById(R.id.dateTextView);
    startDateText.setText(R.string.pick_start_date);
    endDateText.setText(R.string.pick_end_date);

    // Set up listeners
    DateDisplayListener startDateDisplayListener = new DateDisplayListener(getContext(), startDateText);
    DateDisplayListener endDateDisplayListener = new DateDisplayListener(getContext(), endDateText);
    //startDateDisplayListener.setLimitedDatePicker(endDateDisplayListener.getDatePickerDialog());
    startDateDisplay.setOnClickListener(startDateDisplayListener);
    endDateDisplay.setOnClickListener(endDateDisplayListener);

    // Set up city spinner
    spinCity = view.findViewById(R.id.spinCity);
    spinCity.setAdapter(new CitySimplifiedSpinnerAdapter(
        getContext(),
        R.layout.single_city_simplified,
        R.id.cityNameTextView,
        citiesWithVenues));

    // Set up button listener
    view.findViewById(R.id.searchButton)
        .setOnClickListener(this::onSearchClick);

    view.findViewById(R.id.searchButton)
        .setOnClickListener(view1 ->
            NavHostFragment.findNavController(CreateBookingDateFragment.this)
        .navigate(R.id.action_FirstFragment_to_SecondFragment));
  }

  private void onSearchClick(View view) {
  }
}
