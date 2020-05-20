package com.example.timetomeet.activity;

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

import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.example.timetomeet.customview.CitySimplifiedSpinnerAdapter;
import com.example.timetomeet.customview.DateDisplayListener;
import com.example.timetomeet.retrofit.entity.CitySimplified;

import java.util.ArrayList;
import java.util.List;

/**
 * This fragment is used to select a date or time span in which
 * to search for available rooms.
 */
public class CreateBookingDateFragment extends Fragment {
  private ConstraintLayout startDateDisplay, endDateDisplay;
  private Spinner spinCity;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState
  ) {
    Log.i(Logging.CreateBookingActivity, "Inflating fragment 1");
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_create_booking_date, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    startDateDisplay = view.findViewById(R.id.startDateDisplay);
    endDateDisplay = view.findViewById(R.id.endDateDisplay);
    spinCity = view.findViewById(R.id.spinCity);
    TextView startDateText = startDateDisplay.findViewById(R.id.dateTextView);
    TextView endDateText = endDateDisplay.findViewById(R.id.dateTextView);

    startDateText.setText(R.string.pick_start_date);
    endDateText.setText(R.string.pick_end_date);
    startDateDisplay.setOnClickListener(new DateDisplayListener(getContext(), startDateText));
    endDateDisplay.setOnClickListener(new DateDisplayListener(getContext(), endDateText));

    List<CitySimplified> cityList = new ArrayList<>();
    cityList.add(new CitySimplified(1L, "Lol", "Hihi"));
    cityList.add(new CitySimplified(2L, "Lmao", "Haha"));
    cityList.add(new CitySimplified(3L, "Rofl", "Hoho"));

    spinCity.setAdapter(new CitySimplifiedSpinnerAdapter(
        getContext(),
        R.layout.single_city_simplified,
        R.id.cityNameTextView,
        cityList));

    view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        NavHostFragment.findNavController(CreateBookingDateFragment.this)
            .navigate(R.id.action_FirstFragment_to_SecondFragment);
      }
    });
  }
}