package com.example.timetomeet.activity.createbooking;

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
import com.example.timetomeet.retrofit.entity.availableroom.AvailableRoom;
import com.example.timetomeet.retrofit.entity.CitySimplified;

import java.util.List;
import java.util.Map;

public class SearchResultFragment extends Fragment {
  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState
  ) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_search_result, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    //Get bundle from first fragment
    CreateBookingActivity createBookingActivity = (CreateBookingActivity) getActivity();
    Bundle bookingBundle = createBookingActivity.getBookingBundle();
    Bundle apiData = getActivity().getIntent().getExtras();
    List<AvailableRoom> availableRooms = bookingBundle.getParcelableArrayList(Helper.BUNDLE_AVAILABLE_ROOMS_LIST);

    Map<Long, CitySimplified> citiesMap = createBookingActivity.getCityMap();
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
