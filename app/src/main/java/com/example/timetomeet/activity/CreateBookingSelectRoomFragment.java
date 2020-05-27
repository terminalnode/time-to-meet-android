package com.example.timetomeet.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timetomeet.Helper;
import com.example.timetomeet.R;
import com.example.timetomeet.customview.adapters.AvailableRoomsRecyclerAdapter;
import com.example.timetomeet.retrofit.entity.AvailableRoom;
import com.example.timetomeet.retrofit.entity.CitySimplified;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CreateBookingSelectRoomFragment extends Fragment {
  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState
  ) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_create_booking_select_room, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    //Get bundle from first fragment
    Bundle bookingBundle = ((CreateBookingActivity) getActivity()).getBookingBundle();
    Bundle apiData = getActivity().getIntent().getExtras();
    List<AvailableRoom> availableRooms = bookingBundle.getParcelableArrayList(Helper.BUNDLE_AVAILABLE_ROOMS_LIST);
    List<CitySimplified> cities = apiData.getParcelableArrayList(Helper.BUNDLE_CITIES);

    Map<Long, CitySimplified> citiesMap = cities.stream()
        .collect(Collectors.toMap(
            CitySimplified::getId, // Key
            citySimplified -> citySimplified // Value
        ));
    availableRooms.forEach(room -> room.setAssociatedCity(citiesMap.get(room.getCityId())));

    // Add the result list to the recycler view
    RecyclerView recyclerView = view.findViewById(R.id.availableRoomsList);
    AvailableRoomsRecyclerAdapter adapter = new AvailableRoomsRecyclerAdapter(
        getContext(), availableRooms, this, bookingBundle
    );
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setAdapter(adapter);
  }
}
