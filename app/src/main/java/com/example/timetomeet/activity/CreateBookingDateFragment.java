package com.example.timetomeet.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
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
import com.example.timetomeet.retrofit.RetrofitHelper;
import com.example.timetomeet.retrofit.entity.AvailableRoom;
import com.example.timetomeet.retrofit.entity.AvailableRoomsContainer;
import com.example.timetomeet.retrofit.entity.AvailableRoomsQuery;
import com.example.timetomeet.retrofit.entity.CitySimplified;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This fragment is used to select a date or time span in which
 * to search for available rooms.
 */
public class CreateBookingDateFragment extends Fragment {
  private ConstraintLayout startDateDisplay, endDateDisplay;
  private DateDisplayListener startDateDisplayListener, endDateDisplayListener;
  private CitySimplifiedSpinnerAdapter citySpinnerAdapter;
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
    startDateDisplayListener = new DateDisplayListener(getContext(), startDateText);
    endDateDisplayListener = new DateDisplayListener(getContext(), endDateText);
    startDateDisplay.setOnClickListener(startDateDisplayListener);
    endDateDisplay.setOnClickListener(endDateDisplayListener);

    // Set up city spinner
    spinCity = view.findViewById(R.id.spinCity);
    citySpinnerAdapter = new CitySimplifiedSpinnerAdapter(
        getContext(),
        R.layout.single_city_simplified,
        R.id.cityNameTextView,
        citiesWithVenues);
    spinCity.setAdapter(citySpinnerAdapter);

    // Set up button listener
    view.findViewById(R.id.searchButton)
        .setOnClickListener(this::onSearchClick);

    /*
    view.findViewById(R.id.searchButton)
        .setOnClickListener(view1 -> NavHostFragment
            .findNavController(CreateBookingDateFragment.this)
            .navigate(R.id.action_FirstFragment_to_SecondFragment)
        );
     */
  }

  private void onSearchClick(View view) {
    Log.i(Logging.CreateBookingActivity, "We be searching baby");

    // Get start date
    DatePicker startDP = startDateDisplayListener.getDatePickerDialog().getDatePicker();
    @SuppressLint("DefaultLocale") String startDate = String.format(
        "%d-%02d-%02d",
        startDP.getYear(),
        startDP.getMonth() + 1,
        startDP.getDayOfMonth()
    );
    Log.i(Logging.CreateBookingActivity, "Start date: " + startDate);

    // Get end date
    DatePicker endDP = endDateDisplayListener.getDatePickerDialog().getDatePicker();
    @SuppressLint("DefaultLocale") String endDate = String.format(
        "%d-%02d-%02d",
        endDP.getYear(),
        endDP.getMonth() + 1,
        endDP.getDayOfMonth()
    );
    Log.i(Logging.CreateBookingActivity, "End date: " + endDate);

    // Get city
    CitySimplified city = (CitySimplified) spinCity.getSelectedItem();
    Log.i(Logging.CreateBookingActivity, "Selected city: " + city);

    // Making API call
    AvailableRoomsQuery query = new AvailableRoomsQuery("" + city.getId(), startDate, endDate);
    RetrofitHelper.searchAvailableRoomsByCity(query)
        .enqueue(new Callback<AvailableRoomsContainer>() {
          @Override
          public void onResponse(Call<AvailableRoomsContainer> call, Response<AvailableRoomsContainer> response) {
            if (response.body() == null) {
              somethingWentWrongSnackbar(view);

            } else if (response.body().getResult().size() == 0) {
              emptyResultSnackbar(view);

            } else {
              ArrayList<AvailableRoom> availableRooms = new ArrayList<>(response.body().getResult());
              Log.i(Logging.CreateBookingActivity, "Query result: " + availableRooms);
              ((CreateBookingActivity) getActivity())
                  .getBookingBundle()
                  .putParcelableArrayList(Helper.BUNDLE_AVAILABLE_ROOMS_LIST, availableRooms);
              Log.i("YOLO", "All bundled up");

              NavHostFragment
                  .findNavController(CreateBookingDateFragment.this)
                  .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
          }

          @Override
          public void onFailure(Call<AvailableRoomsContainer> call, Throwable t) {
            somethingWentWrongSnackbar(view);
          }
        });
  }

  private void somethingWentWrongSnackbar(View view) {
    Snackbar.make(
        view, R.string.something_went_wrong, Snackbar.LENGTH_LONG
    ).show();
  }

  private void emptyResultSnackbar(View view) {
    Snackbar.make(
        view, R.string.no_search_results, Snackbar.LENGTH_LONG
    ).show();
  }
}
