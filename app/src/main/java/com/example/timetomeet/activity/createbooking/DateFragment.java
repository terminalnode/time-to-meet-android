package com.example.timetomeet.activity.createbooking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.timetomeet.Helper;
import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.example.timetomeet.customview.adapters.CitySimplifiedSpinnerAdapter;
import com.example.timetomeet.customview.DateDisplayListener;
import com.example.timetomeet.retrofit.RetrofitHelper;
import com.example.timetomeet.retrofit.entity.availableroom.AvailableRoom;
import com.example.timetomeet.retrofit.entity.availableroom.AvailableRoomsContainer;
import com.example.timetomeet.retrofit.entity.availableroom.AvailableRoomsQuery;
import com.example.timetomeet.retrofit.entity.CitySimplified;
import com.example.timetomeet.retrofit.entity.Venue;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This fragment is used to select a date or time span in which
 * to search for available rooms.
 */
public class DateFragment extends Fragment {
  private Button searchButton;
  private ConstraintLayout startDateDisplay, endDateDisplay;
  private CitySimplifiedSpinnerAdapter citySpinnerAdapter;
  private DateDisplayListener startDateDisplayListener, endDateDisplayListener;
  private List<CitySimplified> citiesWithVenues;
  private ProgressBar progressBar;
  private Runnable activityStarter;
  private Spinner spinCity;

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
    return inflater.inflate(R.layout.fragment_date, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    // Find progress bar
    progressBar = view.findViewById(R.id.searchProgressBar);

    // Set up date labels
    startDateDisplay = view.findViewById(R.id.startDateDisplay);
    endDateDisplay = view.findViewById(R.id.endDateDisplay);
    TextView startDateText = startDateDisplay.findViewById(R.id.dateTextView);
    TextView endDateText = endDateDisplay.findViewById(R.id.dateTextView);

    // Initialize date labels to today's date
    LocalDateTime ldt = LocalDateTime.now();
    String today = String.format(
        "%s-%02d-%02d",
        ldt.getYear(),
        ldt.getMonthValue(),
        ldt.getDayOfMonth());
    startDateText.setText(today);
    endDateText.setText(today);

    // Set up listeners
    startDateDisplayListener = new DateDisplayListener(getContext(), startDateText);
    endDateDisplayListener = new DateDisplayListener(getContext(), endDateText);
    startDateDisplay.setOnClickListener(startDateDisplayListener);
    endDateDisplay.setOnClickListener(endDateDisplayListener);

    // Set up city spinner
    spinCity = view.findViewById(R.id.spinCity);
    citySpinnerAdapter = new CitySimplifiedSpinnerAdapter(getContext(), citiesWithVenues);
    spinCity.setAdapter(citySpinnerAdapter);

    // Set up button listener
    searchButton = view.findViewById(R.id.searchButton);
    searchButton.setOnClickListener(this::onSearchClick);
  }

  /**
   * Gathers the data from the search form and executes a search.
   * @param view The view in which the button was clicked.
   */
  private void onSearchClick(View view) {
    Log.i(Logging.CreateBookingActivity, "We be searching baby");
    setSearchInProgress();

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
              Log.i(
                  Logging.CreateBookingActivity,
                  "Number of query results: " + availableRooms.size());

              // Get list of all venue ids to venues
              List<Long> venueIds = availableRooms
                  .stream()
                  .map(AvailableRoom::getPlantId)
                  .distinct()
                  .filter(Objects::nonNull)
                  .collect(Collectors.toList());

              //Activity handler
              fetchVenuesAndStartActivity(venueIds, availableRooms);
            }
          }

          @Override
          public void onFailure(Call<AvailableRoomsContainer> call, Throwable t) {
            somethingWentWrongSnackbar(view);
            setSearchFinished();
          }
        });
  }

  /**
   * Fetch all the venues from the list of available rooms, and associate them with the rooms.
   * Then start the next activity, showing a list of all available rooms.
   * @param venueIds The list of (unique) venue IDs.
   * @param availableRooms The list of available rooms.
   */
  private void fetchVenuesAndStartActivity(List<Long> venueIds, ArrayList<AvailableRoom> availableRooms) {
    long retryDelay = 100;
    int totalNumberOfVenues = venueIds.size();
    Map<Long, Venue> mapFetchedVenues = new ConcurrentHashMap<>();

    for (Long id : venueIds) {
      RetrofitHelper.getVenueById(id).enqueue(new Callback<Venue>() {
        @Override
        public void onResponse(Call<Venue> call, Response<Venue> response) {
          mapFetchedVenues.put(id, response.body());
        }

        @Override
        public void onFailure(Call<Venue> call, Throwable t) {
          mapFetchedVenues.put(id, null);
        }
      });
    }

    Handler activityStartHandler = new Handler();
    activityStarter = () -> {
      Log.i(
          Logging.CreateBookingActivity,
          String.format("Fetched venues %s / %s", mapFetchedVenues.size(), totalNumberOfVenues));

      if (mapFetchedVenues.size() == totalNumberOfVenues) {

        // Associate Venues with AvailableRooms
        availableRooms.forEach(x -> x.setAssociatedVenue(mapFetchedVenues.get(x.getPlantId())));

        // Bundle the parcelable rooms
        ((CreateBookingActivity) getActivity())
            .getBookingBundle()
            .putParcelableArrayList(Helper.BUNDLE_AVAILABLE_ROOMS_LIST, availableRooms);
        Log.i(Logging.CreateBookingActivity, "All bundled up");

        NavHostFragment
            .findNavController(DateFragment.this)
            .navigate(R.id.action_DateFragment_to_SearchResultFragment);
        setSearchFinished();
      } else {
        activityStartHandler.postDelayed(activityStarter, retryDelay);
      }
    };

    activityStartHandler.postDelayed(activityStarter, retryDelay);
  }

  /**
   * Indicate that the search is active by deactivating the search button
   * and showing the progress bar.
   */
  private void setSearchInProgress() {
    progressBar.setVisibility(View.VISIBLE);
    searchButton.setClickable(false);
    searchButton.setAlpha(0.5F);
  }

  /**
   * Indicate that the search is finished by reactiving the search button
   * and hiding the progress bar.
   */
  private void setSearchFinished() {
    progressBar.setVisibility(View.GONE);
    searchButton.setClickable(true);
    searchButton.setAlpha(1F);
  }

  /**
   * Show a snackbar saying something went wrong.
   * @param view The view in which to display the snackbar.
   */
  private void somethingWentWrongSnackbar(View view) {
    Snackbar.make(
        view, R.string.something_went_wrong, Snackbar.LENGTH_LONG
    ).show();
  }

  /**
   * Show a snackbar saying the result list was empty.
   * @param view The view in which to display the snackbar.
   */
  private void emptyResultSnackbar(View view) {
    Snackbar.make(
        view, R.string.no_search_results, Snackbar.LENGTH_LONG
    ).show();
  }
}
