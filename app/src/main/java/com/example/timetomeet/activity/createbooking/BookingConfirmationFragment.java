package com.example.timetomeet.activity.createbooking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timetomeet.Helper;
import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.RetrofitHelper;
import com.example.timetomeet.retrofit.entity.bookingconfirmation.BookingConfirmation;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingConfirmationFragment extends Fragment {
  private String token;

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState
  ) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_booking_confirmation, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    token = getActivity().getIntent().getStringExtra(Helper.BUNDLE_TOKEN);

    RetrofitHelper
        .finalizeBooking(token)
        .enqueue(new Callback<BookingConfirmation>() {
          @Override
          public void onResponse(Call<BookingConfirmation> call, Response<BookingConfirmation> response) {
            Log.i("YOLO", "responseBody=" + response.body());
            response.body().prettyPrint();
          }

          @Override
          public void onFailure(Call<BookingConfirmation> call, Throwable t) {
            Log.i("YOLO", "awaddafuk");
          }
        });
  }
}