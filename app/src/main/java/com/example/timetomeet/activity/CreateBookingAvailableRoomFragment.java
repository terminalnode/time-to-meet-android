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
import com.example.timetomeet.customview.adapters.AvailableTechnologiesRecyclerAdapter;
import com.example.timetomeet.retrofit.RetrofitHelper;
import com.example.timetomeet.retrofit.entity.AvailableRoom;
import com.example.timetomeet.retrofit.entity.ConferenceRoom;
import com.example.timetomeet.retrofit.entity.Venue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateBookingAvailableRoomFragment extends Fragment {
  private RecyclerView technologyAvailabilityListView;

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
    technologyAvailabilityListView = view.findViewById(R.id.technologyAvailabilityListView);

    Bundle bookingBundle = ((CreateBookingActivity) getActivity()).getBookingBundle();
    AvailableRoom selectedRoom = bookingBundle.getParcelable(Helper.BUNDLE_SELECTED_ROOM);

    String locale = Helper.getLocale();
    venueNameTextView.setText(Helper.getLocalizedName(selectedRoom.getAssociatedVenue(), locale));
    cityNameTextView.setText(Helper.getLocalizedName(selectedRoom.getAssociatedCity(), locale));
    beforeNoonPriceTextView.setText(String.format("%.02f kr", selectedRoom.getPreNoonPrice()));
    afterNoonPriceTextView.setText(String.format("%.02f kr", selectedRoom.getAfterNoonPrice()));
    fullDayPriceTextView.setText(String.format("%.02f kr", selectedRoom.getFullDayPrice()));

    if (selectedRoom.getAssociatedConferenceRoom() == null) {
      fetchConferenceRoom(selectedRoom, amOpeningHoursTimeTextView, pmOpeningHoursTimeTextView);
    } else {
      setOpeningHours(selectedRoom, amOpeningHoursTimeTextView, pmOpeningHoursTimeTextView);
    }
  }

  private void fetchConferenceRoom(AvailableRoom selectedRoom, TextView am, TextView pm) {
    RetrofitHelper
        .getConferenceRoomById(
            selectedRoom.getRoomId()
        ).enqueue(new Callback<ConferenceRoom>() {
      @Override
      public void onResponse(Call<ConferenceRoom> call, Response<ConferenceRoom> response) {
        selectedRoom.setAssociatedConferenceRoom(response.body());
        Log.i(Logging.CreateBookingActivity, "Fetched " + response.body());

        setOpeningHours(selectedRoom, am, pm);
        technologyAvailabilityListView.setLayoutManager(new LinearLayoutManager(getContext()));
        technologyAvailabilityListView.setAdapter(
            new AvailableTechnologiesRecyclerAdapter(getContext(), response.body().getTechnologies())
        );
      }

      @Override
      public void onFailure(Call<ConferenceRoom> call, Throwable t) {
      }
    });
  }

  private void setOpeningHours(AvailableRoom selectedRoom, TextView am, TextView pm) {
    ConferenceRoom cr = selectedRoom.getAssociatedConferenceRoom();

    am.setText(String.format("%s - %s", cr.getBeforeNoonHourStart(), cr.getBeforeNoonHourEnd()));
    pm.setText(String.format("%s - %s", cr.getAfterNoonHourStart(), cr.getAfterNoonHourEnd()));
  }
}
