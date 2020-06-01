package com.example.timetomeet.activity.createbooking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.timetomeet.Helper;
import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.example.timetomeet.customview.adapters.SelectFoodRecyclerAdapter;
import com.example.timetomeet.customview.adapters.SelectTechnologyRecyclerAdapter;
import com.example.timetomeet.retrofit.RetrofitHelper;
import com.example.timetomeet.retrofit.entity.AvailableRoom;
import com.example.timetomeet.retrofit.entity.BookingFoodBeverageAdd;
import com.example.timetomeet.retrofit.entity.BookingSelectableTechnologyAdd;
import com.example.timetomeet.retrofit.entity.ConferenceRoom;
import com.example.timetomeet.retrofit.entity.ConferenceRoomTechnology;
import com.example.timetomeet.retrofit.entity.FoodBeverage;
import com.example.timetomeet.retrofit.entity.Technology;
import com.example.timetomeet.retrofit.entity.Venue;
import com.example.timetomeet.retrofit.entity.VenueFoodBeverage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodFragment extends Fragment {
  private AvailableRoom selectedRoom;
  private ConferenceRoom conferenceRoom;
  private Map<Long, Technology> technologyMap;
  private Map<Long, FoodBeverage> foodMap;
  private Button confirmButton;
  private String token;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_food, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    CreateBookingActivity activity = (CreateBookingActivity) getActivity();
    Bundle bookingBundle = activity.getBookingBundle();
    token = activity.getIntent().getStringExtra(Helper.BUNDLE_TOKEN);
    selectedRoom = bookingBundle.getParcelable(Helper.BUNDLE_SELECTED_ROOM);
    conferenceRoom = selectedRoom.getAssociatedConferenceRoom();
    technologyMap = activity.getTechnologyMap();
    foodMap = activity.getFoodMap();
    confirmButton = view.findViewById(R.id.confirmButton);

    // Fetch the selectedRoom's associatedVenue's FoodBeverage options,
    // if it is already fetched, just set up the recycler adapter.
    Venue associatedVenue = selectedRoom.getAssociatedVenue();
    if (associatedVenue.getAssociatedFoodBeverages() == null) {
      fetchVenueFoodBeverage();
    } else {
      setUpFoodRecyclerAdapter(associatedVenue);
    }

    // Fetch the conference room's associated conference room technology,
    // if it is already fetched, just set up the recycler adapter.
    if (conferenceRoom.getAssociatedConferenceRoomTechnologies() == null) {
      fetchConferenceRoomTechnology();
    } else {
      setUpTechRecyclerAdapter();
    }

    // Set onClick for confirmButton
    confirmButton.setOnClickListener(this::confirmButtonClicked);
  }

  private void confirmButtonClicked(View view) {
    List<VenueFoodBeverage> venueFoodBeverages = selectedRoom
        .getAssociatedVenue()
        .getAssociatedFoodBeverages()
        .stream()
        .filter(VenueFoodBeverage::isSelected)
        .collect(Collectors.toList());

    List<ConferenceRoomTechnology> conferenceRoomTechnologies = selectedRoom
        .getAssociatedConferenceRoom()
        .getAssociatedConferenceRoomTechnologies()
        .stream()
        .filter(ConferenceRoomTechnology::isSelected)
        .collect(Collectors.toList());

    List<BookingFoodBeverageAdd> finishedVenueFoodBeverages = new ArrayList<>();
    List<BookingSelectableTechnologyAdd> finishedConferenceRoomTechnologies = new ArrayList<>();

    venueFoodBeverages.forEach(venueFoodBeverage -> {
      addFoodBeverage(venueFoodBeverage, finishedVenueFoodBeverages);
    });
    conferenceRoomTechnologies.forEach(conferenceRoomTechnology -> {
      addSelectableTechnology(conferenceRoomTechnology, finishedConferenceRoomTechnologies);
    });
  }

  private void addFoodBeverage(VenueFoodBeverage vfb, List<BookingFoodBeverageAdd> finishedVenueFoodBeverages) {
    BookingFoodBeverageAdd bfba = new BookingFoodBeverageAdd();
    bfba.setConferenceRoomAvailability("YOLO");
    bfba.setFoodBeverageId(vfb.getFoodBeverage());
    bfba.setAmount(1); // TODO Add amount field
    bfba.setComment(""); // TODO Add comment field
    bfba.setTimeToServe(String.format("%sT%s", selectedRoom.getStartDate(), vfb.getSelectedTime()));

    RetrofitHelper
        .addFoodBeverage(token, bfba)
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

  private void addSelectableTechnology(ConferenceRoomTechnology conferenceRoomTechnology, List<BookingSelectableTechnologyAdd> finishedConferenceRoomTechnologies) {
    BookingSelectableTechnologyAdd bsta = new BookingSelectableTechnologyAdd();
    bsta.setConferenceRoomAvailability("YOLO");
    bsta.setTechnology(conferenceRoomTechnology.getTechnology());

    RetrofitHelper
        .addSelectableTechnology(token, bsta)
        .enqueue(new Callback<BookingSelectableTechnologyAdd>() {
          @Override
          public void onResponse(Call<BookingSelectableTechnologyAdd> call, Response<BookingSelectableTechnologyAdd> response) {
            Log.i(Logging.CreateBookingActivity, "Successfully added " + response.body());
            finishedConferenceRoomTechnologies.add(response.body());
          }

          @Override
          public void onFailure(Call<BookingSelectableTechnologyAdd> call, Throwable t) {
          }
        });
  }

  private void fetchVenueFoodBeverage() {
    Venue associatedVenue = selectedRoom.getAssociatedVenue();

    RetrofitHelper
        .getPlantFoodBeverage(associatedVenue.getId())
        .enqueue(new Callback<List<VenueFoodBeverage>>() {
          @Override
          public void onResponse(Call<List<VenueFoodBeverage>> call, Response<List<VenueFoodBeverage>> response) {
            associatedVenue.setAssociatedFoodBeverages(response.body());
            setUpFoodRecyclerAdapter(associatedVenue);
          }

          @Override
          public void onFailure(Call<List<VenueFoodBeverage>> call, Throwable t) {
          }
        });
  }

  private void setUpFoodRecyclerAdapter(Venue associatedVenue) {
    RecyclerView foodRecyclerView = getView().findViewById(R.id.foodRecyclerView);
    SelectFoodRecyclerAdapter foodAdapter = new SelectFoodRecyclerAdapter(
        getContext(),
        associatedVenue.getAssociatedFoodBeverages(),
        foodMap
    );
    foodRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    foodRecyclerView.setAdapter(foodAdapter);
  }

  private void fetchConferenceRoomTechnology() {
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

  private void setUpTechRecyclerAdapter() {
    RecyclerView techRecyclerView = getView().findViewById(R.id.technologyRecyclerView);
    SelectTechnologyRecyclerAdapter techAdapter = new SelectTechnologyRecyclerAdapter(
        getContext(),
        conferenceRoom.getAssociatedConferenceRoomTechnologies(),
        technologyMap
    );
    techRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    techRecyclerView.setAdapter(techAdapter);
  }
}