package com.example.timetomeet.activity.createbooking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class RoomDetailsFragment extends Fragment {
  private Button createBookingButton;
  TextView beforeNoonPriceTextView;
  TextView afterNoonPriceTextView;
  TextView fullDayPriceTextView;

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState
  ) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_room_details, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
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
    beforeNoonPriceTextView = view.findViewById(R.id.beforeNoonPriceTextView);
    afterNoonPriceTextView = view.findViewById(R.id.afterNoonPriceTextView);
    fullDayPriceTextView = view.findViewById(R.id.fullDayPriceTextView);
    beforeNoonPriceTextView.setText(String.format("%.02f kr", selectedRoom.getPreNoonPrice()));
    afterNoonPriceTextView.setText(String.format("%.02f kr", selectedRoom.getAfterNoonPrice()));
    fullDayPriceTextView.setText(String.format("%.02f kr", selectedRoom.getFullDayPrice()));

    // Disable the create booking button until information is fetched.
    createBookingButton = view.findViewById(R.id.createBookingButton);
    createBookingButton.setClickable(false);
    createBookingButton.setAlpha(0.5F);
    createBookingButton.setOnClickListener(this::createBookingButtonClicked);

    // Fetch information from the associated conference room,
    // fetching the conference room if necessary.
    if (selectedRoom.getAssociatedConferenceRoom() == null) {
      fetchConferenceRoom(selectedRoom);
    } else {
      setAfterRoomFetch(selectedRoom);
    }
  }

  /**
   * Open the next fragment when the button is clicked,
   * which is the CreateBookingConfirmRoomFragment.
   * @param view The view in which the button is clicked.
   */
  private void createBookingButtonClicked(View view) {
    // TODO Make sure to send the information to the next fragment.
    // TODO Make sure all the necessary API calls are being made.
    Log.i(Logging.CreateBookingActivity, "Moving over to confirm room fragment");
    NavHostFragment
        .findNavController(RoomDetailsFragment.this)
        .navigate(R.id.action_RoomDetailsFragment_to_ConfirmRoomFragment);
  }

  /**
   * Fetch the selected room's associated conference room.
   * @param selectedRoom The selected room we're fetching the conference room for.
   */
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

  /**
   * Set all the fields for which we need the information from the conference room.
   * @param selectedRoom The selected room we use to get the conference room.
   */
  private void setAfterRoomFetch(AvailableRoom selectedRoom) {
    ConferenceRoom room = selectedRoom.getAssociatedConferenceRoom();
    View view = getView();

    // Set opening/closing hours for before noon
    TextView am = view.findViewById(R.id.amOpeningHoursTimeTextView);
    am.setText(String.format(
        "%s - %s",
        room.getBeforeNoonHourStart(),
        room.getBeforeNoonHourEnd()));

    // Set opening/closing hours for afternoon
    TextView pm = view.findViewById(R.id.pmOpeningHoursTimeTextView);
    pm.setText(String.format(
        "%s - %s",
        room.getAfterNoonHourStart(),
        room.getAfterNoonHourEnd()));

    // Dim unavailable hours and prices
    if (selectedRoom.getId31() == null) {
      am.setAlpha(0.5F);
      beforeNoonPriceTextView.setAlpha(0.5F);
      view.findViewById(R.id.beforeNoonTextView).setAlpha(0.5F);
      view.findViewById(R.id.amOpeningHoursTextView).setAlpha(0.5F);

    } else if (selectedRoom.getId32() == null) {
      pm.setAlpha(0.5F);
      afterNoonPriceTextView.setAlpha(0.5F);
      view.findViewById(R.id.afterNoonTextView).setAlpha(0.5F);
      view.findViewById(R.id.pmOpeningHoursTextView).setAlpha(0.5F);
    }

    // Dim full day prices if either AM or PM time slots are unavailable
    if (selectedRoom.getId31() == null || selectedRoom.getId32() == null) {
      fullDayPriceTextView.setAlpha(0.5F);
      view.findViewById(R.id.fullDayTextView).setAlpha(0.5F);
    }

    // List available technologies
    RecyclerView technologyRecyclerView = view.findViewById(R.id.technologyAvailabilityRecyclerView);
    technologyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    technologyRecyclerView.setAdapter(
        new AvailableTechnologiesRecyclerAdapter(getContext(), room.getTechnologies())
    );

    // List available seating options
    RecyclerView seatingRecyclerView = view.findViewById(R.id.seatingAlternativesRecyclerView);
    seatingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    seatingRecyclerView.setAdapter(
        new AvailableSeatingsRecyclerAdapter(getContext(), room)
    );

    // Set the description
    TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
    String descriptionText = Helper.getLocalizedDescription(room, getContext());
    descriptionTextView.setText(descriptionText);

    // Reenable the createBookingButton
    createBookingButton.setClickable(true);
    createBookingButton.setAlpha(1F);
  }
}
