package com.example.timetomeet.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.timetomeet.Helper;
import com.example.timetomeet.R;
import com.example.timetomeet.customview.AvailableRoomListAdapter;
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

    ListView availableRoomsList = view.findViewById(R.id.availableRoomsList);
    availableRoomsList.setAdapter(
        new AvailableRoomListAdapter(
            getContext(),
            availableRooms,
            this,
            bookingBundle
        )
    );
  }
}
