package com.example.timetomeet.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.timetomeet.Helper;
import com.example.timetomeet.R;
import com.example.timetomeet.customview.AvailableRoomListAdapter;

public class SecondFragment extends Fragment {

  Bundle bookingBundle;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState
  ) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_second, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    //Get bundle from first fragment
    bookingBundle = ((CreateBookingActivity) getActivity()).getBookingBundle();

    ListView availableRoomsList = view.findViewById(R.id.availableRoomsList);

    availableRoomsList.setAdapter(
        new AvailableRoomListAdapter(
            getContext(),
            R.layout.single_city_simplified,
            bookingBundle.getParcelableArrayList(Helper.BUNDLE_AVAILABLE_ROOMS_LIST)));

    view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        NavHostFragment.findNavController(SecondFragment.this)
            .navigate(R.id.action_SecondFragment_to_FirstFragment);
      }
    });
  }
}
