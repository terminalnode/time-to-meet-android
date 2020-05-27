package com.example.timetomeet.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.timetomeet.Helper;
import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.example.timetomeet.customview.adapters.AvailableSeatingsRecyclerAdapter;
import com.example.timetomeet.customview.adapters.AvailableTechnologiesRecyclerAdapter;
import com.example.timetomeet.retrofit.RetrofitHelper;
import com.example.timetomeet.retrofit.entity.AvailableRoom;
import com.example.timetomeet.retrofit.entity.ConferenceRoom;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateBookingAvailableRoomFragment extends Fragment {
  private TextView amOpeningHoursTimeTextView;
  private TextView pmOpeningHoursTimeTextView;
  private RecyclerView technologyAvailabilityListView;
  private RecyclerView seatingAlternativesListView;

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState
  ) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_create_booking_available_room, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    amOpeningHoursTimeTextView = view.findViewById(R.id.amOpeningHoursTimeTextView);
    pmOpeningHoursTimeTextView = view.findViewById(R.id.pmOpeningHoursTimeTextView);
    technologyAvailabilityListView = view.findViewById(R.id.technologyAvailabilityListView);
    seatingAlternativesListView = view.findViewById(R.id.seatingAlternativesListView);

    // Get the selected room from booking bundle
    Bundle bookingBundle = ((CreateBookingActivity) getActivity()).getBookingBundle();
    AvailableRoom selectedRoom = bookingBundle.getParcelable(Helper.BUNDLE_SELECTED_ROOM);

    // Set starting date
    TextView dateTextView = view.findViewById(R.id.dateTextView);
    dateTextView.setText(selectedRoom.getStartDate());

    // Set venue and city name using the correct locale
    TextView venueNameTextView = view.findViewById(R.id.venueNameTextView);
    TextView cityNameTextView = view.findViewById(R.id.cityNameTextView);
    String locale = Helper.getLocale();
    String venueName = Helper.getLocalizedName(selectedRoom.getAssociatedVenue(),locale, getContext());
    String cityName = Helper.getLocalizedName(selectedRoom.getAssociatedCity(), locale, getContext());
    venueNameTextView.setText(venueName);
    cityNameTextView.setText(cityName);

    // Set prices in text view
    TextView beforeNoonPriceTextView = view.findViewById(R.id.beforeNoonPriceTextView);
    TextView afterNoonPriceTextView = view.findViewById(R.id.afterNoonPriceTextView);
    TextView fullDayPriceTextView = view.findViewById(R.id.fullDayPriceTextView);
    beforeNoonPriceTextView.setText(String.format("%.02f kr", selectedRoom.getPreNoonPrice()));
    afterNoonPriceTextView.setText(String.format("%.02f kr", selectedRoom.getAfterNoonPrice()));
    fullDayPriceTextView.setText(String.format("%.02f kr", selectedRoom.getFullDayPrice()));

    // Fetch information from the associated conference room,
    // fetching the conference room if necessary.
    if (selectedRoom.getAssociatedConferenceRoom() == null) {
      fetchConferenceRoom(selectedRoom);
    } else {
      setAfterRoomFetch(selectedRoom);
    }
  }

  private void fetchConferenceRoom(AvailableRoom selectedRoom) {
    RetrofitHelper
        .getConferenceRoomById(selectedRoom.getRoomId())
        .enqueue(new Callback<ConferenceRoom>() {
          @Override
          public void onResponse(Call<ConferenceRoom> call, Response<ConferenceRoom> response) {
            Log.i(Logging.CreateBookingActivity, "Fetched " + response.body());

            selectedRoom.setAssociatedConferenceRoom(response.body());
            setAfterRoomFetch(selectedRoom);
          }

          @Override
          public void onFailure(Call<ConferenceRoom> call, Throwable t) {
            Snackbar.make(
                getView(),
                R.string.something_went_wrong,
                Snackbar.LENGTH_LONG
            ).show();
          }
        });
  }

  private void setAfterRoomFetch(AvailableRoom selectedRoom) {
    ConferenceRoom room = selectedRoom.getAssociatedConferenceRoom();

    // Set opening/closing hours for before noon
    amOpeningHoursTimeTextView.setText(String.format(
        "%s - %s",
        room.getBeforeNoonHourStart(),
        room.getBeforeNoonHourEnd()));

    // Set opening/closing hours for afternoon
    pmOpeningHoursTimeTextView.setText(String.format(
        "%s - %s",
        room.getAfterNoonHourStart(),
        room.getAfterNoonHourEnd()));

    // List available technologies
    technologyAvailabilityListView.setLayoutManager(new LinearLayoutManager(getContext()));
    technologyAvailabilityListView.setAdapter(
        new AvailableTechnologiesRecyclerAdapter(getContext(), room.getTechnologies())
    );

    // List available seating options
    seatingAlternativesListView.setLayoutManager(new LinearLayoutManager(getContext()));
    seatingAlternativesListView.setAdapter(
        new AvailableSeatingsRecyclerAdapter(getContext(), room)
    );
  }
}
