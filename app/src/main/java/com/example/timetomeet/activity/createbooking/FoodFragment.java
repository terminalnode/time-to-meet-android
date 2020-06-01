package com.example.timetomeet.activity.createbooking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timetomeet.Helper;
import com.example.timetomeet.R;
import com.example.timetomeet.customview.adapters.SelectTechnologyRecyclerAdapter;
import com.example.timetomeet.retrofit.RetrofitHelper;
import com.example.timetomeet.retrofit.entity.AvailableRoom;
import com.example.timetomeet.retrofit.entity.ConferenceRoom;
import com.example.timetomeet.retrofit.entity.ConferenceRoomTechnology;
import com.example.timetomeet.retrofit.entity.Technology;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodFragment extends Fragment {
  private AvailableRoom selectedRoom;
  private ConferenceRoom conferenceRoom;
  private Map<Long, Technology> technologyMap;

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
    selectedRoom = bookingBundle.getParcelable(Helper.BUNDLE_SELECTED_ROOM);
    conferenceRoom = selectedRoom.getAssociatedConferenceRoom();
    technologyMap = activity.getTechnologyMap();

    // Fetch the conference room's associated conference room technology,
    // if it is already fetched, just set up the recycler adapter.
    if (conferenceRoom.getAssociatedConferenceRoomTechnologies() == null) {
      fetchConferenceRoomTechnology();
    } else {
      setUpTechRecyclerAdapter();
    }
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