package com.example.timetomeet.activity.createbooking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.example.timetomeet.customview.adapters.SelectFoodRecyclerAdapter;
import com.example.timetomeet.customview.adapters.SelectTechnologyRecyclerAdapter;
import com.example.timetomeet.retrofit.RetrofitHelper;
import com.example.timetomeet.retrofit.entity.BookingFoodBeverageAdd;
import com.example.timetomeet.retrofit.entity.BookingSelectableTechnologyAdd;
import com.example.timetomeet.retrofit.entity.conferenceroom.ConferenceRoom;
import com.example.timetomeet.retrofit.entity.conferenceroom.ConferenceRoomTechnology;
import com.example.timetomeet.retrofit.entity.Venue;
import com.example.timetomeet.retrofit.entity.VenueFoodBeverage;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodFragment extends Fragment {
  private Button confirmButton;
  private ProgressBar techProgressBar;
  private ProgressBar foodProgressBar;
  private ProgressBar mainProgressBar;
  private Runnable confirmBookingActivity;
  private BookingCoordinator bookingCoordinator;
  private View mainView;

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState
  ) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_food, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    CreateBookingActivity activity = (CreateBookingActivity) getActivity();
    bookingCoordinator = activity.getBookingCoordinator();
    mainView = view;

    // Find views
    confirmButton = view.findViewById(R.id.finalizeBookingButton);
    techProgressBar = view.findViewById(R.id.techProgressBar);
    foodProgressBar = view.findViewById(R.id.foodProgressBar);
    mainProgressBar = view.findViewById(R.id.mainProgressBar);

    // Fetch the selectedRoom's associatedVenue's FoodBeverage options,
    // if it is already fetched, just set up the recycler adapter.
    Venue associatedVenue = bookingCoordinator.getSelectedRoom().getAssociatedVenue();
    if (associatedVenue.getAssociatedFoodBeverages() == null) {
      fetchVenueFoodBeverage();
    } else {
      setUpFoodRecyclerAdapter(associatedVenue);
    }

    // Fetch the conference room's associated conference room technology,
    // if it is already fetched, just set up the recycler adapter.
    List<ConferenceRoomTechnology> associatedTech = bookingCoordinator
        .getSelectedRoom()
        .getAssociatedConferenceRoom()
        .getAssociatedConferenceRoomTechnologies();
    if (associatedTech == null) {
      fetchConferenceRoomTechnology();
    } else {
      setUpTechRecyclerAdapter();
    }

    // Set onClick for confirmButton
    confirmButton.setOnClickListener(this::confirmButtonClicked);
  }

  /**
   * When the confirm button is clicked we check that all the selected foods have a date, then
   * request all the food and tech the user has selected. Once that's done we move on to the
   * booking confirmation fragment.
   * @param view The button's view
   */
  private void confirmButtonClicked(View view) {
    startVisuallyLoading();
    List<VenueFoodBeverage> venueFoodBeverages = getCheckedFoodBeverages();
    List<ConferenceRoomTechnology> conferenceRoomTechnologies = getCheckedTechnologies();
    List<BookingFoodBeverageAdd> finishedVenueFoodBeverages = new ArrayList<>();
    List<BookingSelectableTechnologyAdd> finishedConferenceRoomTechnologies = new ArrayList<>();

    // Stop everything if one or more of the checked
    // food/beverage options are missing a date.
    if (!checkedFoodBeveragesHaveDates(venueFoodBeverages)) {
      Snackbar.make(mainView, R.string.please_give_food_times, Snackbar.LENGTH_LONG).show();
      stopVisuallyLoading();
      return;
    }

    // Add all the food
    venueFoodBeverages.forEach(venueFoodBeverage -> {
      addFoodBeverage(venueFoodBeverage, finishedVenueFoodBeverages);
    });

    // Add all the tech
    conferenceRoomTechnologies.forEach(conferenceRoomTechnology -> {
      addSelectableTechnology(conferenceRoomTechnology, finishedConferenceRoomTechnologies);
    });

    // Move on with your life
    startBookingConfirmationFragment(
        venueFoodBeverages,
        conferenceRoomTechnologies,
        finishedVenueFoodBeverages,
        finishedConferenceRoomTechnologies);
  }

  /**
   * Check that all VenueFoodBeverages have a time set.
   * @return Boolean signalling if all VenueFoodBeverages supplied have a time.
   */
  private boolean checkedFoodBeveragesHaveDates(List<VenueFoodBeverage> vfbs) {
    return vfbs.stream()
        .allMatch(x -> x.getSelectedTime() != null);
  }

  /**
   * Get a list of all VenueFoodBeverages where isSelected is true.
   * @return A list of selected VenueFoodBeverages
   */
  private List<VenueFoodBeverage> getCheckedFoodBeverages() {
    return bookingCoordinator
        .getSelectedRoom()
        .getAssociatedVenue()
        .getAssociatedFoodBeverages()
        .stream()
        .filter(VenueFoodBeverage::isSelected)
        .collect(Collectors.toList());
  }

  /**
   * Get a list of all ConferenceRoomTechnology where isSelected is true.
   * @return A list of selected ConferenceRoomTechnologies
   */
  private List<ConferenceRoomTechnology> getCheckedTechnologies() {
    return bookingCoordinator
        .getSelectedRoom()
        .getAssociatedConferenceRoom()
        .getAssociatedConferenceRoomTechnologies()
        .stream()
        .filter(ConferenceRoomTechnology::isSelected)
        .collect(Collectors.toList());
  }

  /**
   * Check that all of the tech and food has been requested, then start the booking
   * confirmation fragment. As in other places of the app, we check that everything's been
   * added by comparing the size of lists with all items vs lists with finished items.
   * @param venueFoodBeverages The full list of venue food beverages to add.
   * @param conferenceRoomTechnologies The full list of conference room technologies to add.
   * @param finishedVenueFoodBeverages The list of added venue food beverages.
   * @param finishedConferenceRoomTechnologies The list of added conference room technologies.
   */
  private void startBookingConfirmationFragment(
      List<VenueFoodBeverage> venueFoodBeverages,
      List<ConferenceRoomTechnology> conferenceRoomTechnologies,
      List<BookingFoodBeverageAdd> finishedVenueFoodBeverages,
      List<BookingSelectableTechnologyAdd> finishedConferenceRoomTechnologies
  ) {
    Handler handler = new Handler();
    long delayMillis = 250;
    int totalNumFood = venueFoodBeverages.size();
    int totalNumTech = conferenceRoomTechnologies.size();

    confirmBookingActivity = () -> {
      boolean foodIsDone = totalNumFood == finishedVenueFoodBeverages.size();
      boolean techIsDone = totalNumTech == finishedConferenceRoomTechnologies.size();
      Log.i(Logging.CreateBookingActivity, "All food requested? " + foodIsDone);
      Log.i(Logging.CreateBookingActivity, "All tech requested? " + techIsDone);
      Log.i(Logging.CreateBookingActivity, "Everything requested? " + (foodIsDone && techIsDone));

      if (foodIsDone && techIsDone) {
        Log.i(Logging.CreateBookingActivity, "Starting Booking Confirmation Fragment");

        stopVisuallyLoading();
        NavHostFragment
            .findNavController(FoodFragment.this)
            .navigate(R.id.action_FoodFragment_to_BookingConfirmationFragment);
      } else {
        handler.postDelayed(confirmBookingActivity, delayMillis);
      }
    };

    handler.postDelayed(confirmBookingActivity, delayMillis);
  }

  /**
   * Make the API call to add a VenueFoodBeverage for the booking.
   * @param vfb The VenueFoodBeverage that we wish to reserve.
   * @param finishedVenueFoodBeverages The list where we save all successful additions.
   */
  private void addFoodBeverage(VenueFoodBeverage vfb, List<BookingFoodBeverageAdd> finishedVenueFoodBeverages) {
    Log.i(Logging.CreateBookingActivity, "Preparing BookingFoodBeverageAdd from: " + vfb);
    BookingFoodBeverageAdd bfba = new BookingFoodBeverageAdd();
    bfba.setConferenceRoomAvailability("" + bookingCoordinator.getTimeSlotId());
    bfba.setFoodBeverageId(vfb.getFoodBeverage());
    bfba.setAmount(vfb.getViewHolder().getNumberOfParticipants());
    bfba.setComment(vfb.getViewHolder().getComment());
    bfba.setTimeToServe(String.format("%sT%s", bookingCoordinator.getSelectedRoom().getStartDate(), vfb.getSelectedTime()));
    Log.i(Logging.CreateBookingActivity, "Prepared BookingFoodBeverageAdd: " + bfba);

    RetrofitHelper
        .addFoodBeverage(bookingCoordinator.getToken(), bfba)
        .enqueue(new Callback<BookingFoodBeverageAdd>() {
          @Override
          public void onResponse(Call<BookingFoodBeverageAdd> call, Response<BookingFoodBeverageAdd> response) {
            Log.i(Logging.CreateBookingActivity, "Successfully added " + response.body());

            finishedVenueFoodBeverages.add(response.body());
          }

          @Override
          public void onFailure(Call<BookingFoodBeverageAdd> call, Throwable t) {
          }
        });
  }

  /**
   * Make the API call to add a ConferenceRoomTechnology for the booking.
   * @param conferenceRoomTechnology The ConferenceRoomTechnology to be added.
   * @param finishedConferenceRoomTechnologies The list where we save all successful additions.
   */
  private void addSelectableTechnology(
      ConferenceRoomTechnology conferenceRoomTechnology,
      List<BookingSelectableTechnologyAdd> finishedConferenceRoomTechnologies
  ) {
    BookingSelectableTechnologyAdd bsta = new BookingSelectableTechnologyAdd();
    bsta.setConferenceRoomAvailability("" + bookingCoordinator.getTimeSlotId());
    bsta.setTechnology(conferenceRoomTechnology.getTechnology());

    RetrofitHelper
        .addSelectableTechnology(bookingCoordinator.getToken(), bsta)
        .enqueue(new Callback<BookingSelectableTechnologyAdd>() {
          @Override
          public void onResponse(
              Call<BookingSelectableTechnologyAdd> call,
              Response<BookingSelectableTechnologyAdd> response
          ) {
            Log.i(Logging.CreateBookingActivity, "Successfully added " + response.body());
            finishedConferenceRoomTechnologies.add(response.body());
          }

          @Override
          public void onFailure(Call<BookingSelectableTechnologyAdd> call, Throwable t) {
          }
        });
  }

  /**
   * Retrieve venue food beverages for the recycler adapter.
   */
  private void fetchVenueFoodBeverage() {
    Venue associatedVenue = bookingCoordinator.getSelectedRoom().getAssociatedVenue();

    RetrofitHelper
        .getPlantFoodBeverage(associatedVenue.getId())
        .enqueue(new Callback<List<VenueFoodBeverage>>() {
          @Override
          public void onResponse(
              Call<List<VenueFoodBeverage>> call,
              Response<List<VenueFoodBeverage>> response
          ) {
            associatedVenue.setAssociatedFoodBeverages(response.body());
            setUpFoodRecyclerAdapter(associatedVenue);
          }

          @Override
          public void onFailure(Call<List<VenueFoodBeverage>> call, Throwable t) {
          }
        });
  }

  /**
   * Add all the food to the recyclerview through a recycler adapter.
   */
  private void setUpFoodRecyclerAdapter(Venue associatedVenue) {
    RecyclerView foodRecyclerView = getView().findViewById(R.id.foodRecyclerView);
    SelectFoodRecyclerAdapter foodAdapter = new SelectFoodRecyclerAdapter(
        getContext(),
        associatedVenue.getAssociatedFoodBeverages(),
        bookingCoordinator.getFoodMap(),
        bookingCoordinator.getSelectedRoomSeating()
    );
    foodRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    foodRecyclerView.setAdapter(foodAdapter);

    foodProgressBar.setVisibility(View.GONE);
  }

  /**
   * Retrieve conference room technologies for the recycler adapter.
   */
  private void fetchConferenceRoomTechnology() {
    ConferenceRoom conferenceRoom = bookingCoordinator
        .getSelectedRoom()
        .getAssociatedConferenceRoom();

    RetrofitHelper
        .getConferenceRoomTechnology(conferenceRoom.getId())
        .enqueue(new Callback<List<ConferenceRoomTechnology>>() {
          @Override
          public void onResponse(Call<List<ConferenceRoomTechnology>> call, Response<List<ConferenceRoomTechnology>> response) {
            conferenceRoom.setAssociatedConferenceRoomTechnologies(response.body());
            setUpTechRecyclerAdapter();
          }

          @Override
          public void onFailure(Call<List<ConferenceRoomTechnology>> call, Throwable t) {
          }
        });
  }

  /**
   * Add all the technologies to the recyclerview through a recycleradapter.
   */
  private void setUpTechRecyclerAdapter() {
    ConferenceRoom conferenceRoom = bookingCoordinator
        .getSelectedRoom()
        .getAssociatedConferenceRoom();

    RecyclerView techRecyclerView = getView().findViewById(R.id.technologyRecyclerView);
    List<ConferenceRoomTechnology> technologies = conferenceRoom.getAssociatedConferenceRoomTechnologies();
    Collections.sort(technologies);

    SelectTechnologyRecyclerAdapter techAdapter = new SelectTechnologyRecyclerAdapter(
        getContext(),
        technologies,
        bookingCoordinator.getTechnologyMap()
    );
    techRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    techRecyclerView.setAdapter(techAdapter);

    techProgressBar.setVisibility(View.GONE);
  }

  /**
   * Show the progress bar and deactivate the confirm button
   * to indicate to the user that something's loading.
   */
  private void startVisuallyLoading() {
    mainProgressBar.setVisibility(View.VISIBLE);
    confirmButton.setClickable(false);
    confirmButton.setAlpha(0.5F);
  }

  /**
   * Hide the progress bar and reactivate the confirm button
   * to indicate to the user that the loading has finished.
   */
  private void stopVisuallyLoading() {
    mainProgressBar.setVisibility(View.GONE);
    confirmButton.setClickable(true);
    confirmButton.setAlpha(1F);
  }
}