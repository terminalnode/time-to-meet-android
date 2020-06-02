package com.example.timetomeet.activity.createbooking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.RetrofitHelper;
import com.example.timetomeet.retrofit.entity.bookingconfirmation.BookingConfirmation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingConfirmationFragment extends Fragment {
  private BookingCoordinator bookingCoordinator;

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
    CreateBookingActivity activity = (CreateBookingActivity) getActivity();
    bookingCoordinator = activity.getBookingCoordinator();

    // Make API-call, until API call is made we have nothing to show.
    RetrofitHelper
        .finalizeBooking(bookingCoordinator.getToken())
        .enqueue(new Callback<BookingConfirmation>() {
          @Override
          public void onResponse(Call<BookingConfirmation> call, Response<BookingConfirmation> response) {
            Log.i("YOLO", "responseBody=" + response.body());
            response.body().prettyPrint();

            onApiCallFinish(view, response.body());
          }

          @Override
          public void onFailure(Call<BookingConfirmation> call, Throwable t) {
            Log.i("YOLO", "awaddafuk");
          }
        });
  }

  private void onApiCallFinish(View view, BookingConfirmation bookingConfirmation) {
    // Set the booking number
    TextView bookingNumberTextView = view.findViewById(R.id.bookingNumberTextView);
    String bookingNumber = String.format("#%s", bookingConfirmation.getBookingDetails().getBookingNumber());
    bookingNumberTextView.setText(bookingNumber);
  }
}