package com.example.timetomeet.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timetomeet.R;

public class CreateBookingAvailableRoomFragment extends Fragment {

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_create_booking_available_room, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
  }
}