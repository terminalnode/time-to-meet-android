package com.example.timetomeet.activity.createbooking;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.example.timetomeet.customview.adapters.BookingConfirmationFoodBeverageRecyclerAdapter;
import com.example.timetomeet.customview.adapters.IWantedToMakeAStringButFailedRecyclerAdapter;
import com.example.timetomeet.retrofit.RetrofitHelper;
import com.example.timetomeet.retrofit.entity.bookingconfirmation.BookingConfirmation;
import com.example.timetomeet.retrofit.entity.bookingconfirmation.BookingConfirmationBookedByPerson;
import com.example.timetomeet.retrofit.entity.bookingconfirmation.BookingConfirmationDetails;
import com.example.timetomeet.retrofit.entity.bookingconfirmation.BookingConfirmationFoodBeverage;
import com.example.timetomeet.retrofit.entity.bookingconfirmation.IWantedToMakeAStringButFailed;

import java.util.List;

import okhttp3.internal.annotations.EverythingIsNonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingConfirmationFragment extends Fragment {
  private BookingCoordinator bookingCoordinator;

  // Top view
  private ProgressBar bookingConfirmationProgressBar;
  private TextView bookingNumberTextView;

  // Booking Information views
  private RecyclerView requestedTechnologyRecyclerView;
  private RecyclerView requestedFoodRecyclerView;
  private TextView dateTextView;
  private TextView arrivalTextView;
  private TextView departureTextView;
  private TextView participantsTextView;
  private TextView costTextView;
  private TextView specialRequestTextView;

  // Personal information views
  private TextView firstNameTextView;
  private TextView lastNameTextView;
  private TextView phoneNumberTextView;
  private TextView emailTextView;
  private TextView organizationNumberTextView;
  private TextView organizationNameTextView;

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
    finalizeBooking(getContext(), view); // Start API call while we find views

    // Find views
    bookingNumberTextView = view.findViewById(R.id.bookingNumberTextView);
    bookingConfirmationProgressBar = view.findViewById(R.id.bookingConfirmationProgressBar);

    dateTextView = view.findViewById(R.id.dateTextView);
    arrivalTextView = view.findViewById(R.id.arrivalTextView);
    departureTextView = view.findViewById(R.id.departureTextView);
    participantsTextView = view.findViewById(R.id.participantsTextView);
    costTextView = view.findViewById(R.id.costTextView);
    specialRequestTextView = view.findViewById(R.id.specialRequestTextView);
    requestedTechnologyRecyclerView = view.findViewById(R.id.requestedTechnologyRecyclerView);
    requestedFoodRecyclerView = view.findViewById(R.id.requestedFoodRecyclerView);

    firstNameTextView = view.findViewById(R.id.firstNameTextView);
    lastNameTextView = view.findViewById(R.id.lastNameTextView);
    phoneNumberTextView = view.findViewById(R.id.phoneNumberTextView);
    emailTextView = view.findViewById(R.id.emailTextView);
    organizationNumberTextView = view.findViewById(R.id.organizationNumberTextView);
    organizationNameTextView = view.findViewById(R.id.organizationNameTextView);
  }

  private void finalizeBooking(Context context, View view) {
    Log.i(Logging.BookingConfirmation, "We be confirming baby");
    RetrofitHelper
        .finalizeBooking(bookingCoordinator.getToken())
        .enqueue(new Callback<BookingConfirmation>() {
          @Override
          @EverythingIsNonNull
          public void onResponse(Call<BookingConfirmation> call, Response<BookingConfirmation> response) {
            Log.i(Logging.BookingConfirmation, "Did confirmation call, booyah!");
            onApiCallFinish(view, response.body());
         }

          @Override
          @EverythingIsNonNull
          public void onFailure(Call<BookingConfirmation> call, Throwable t) {
            Log.e(Logging.BookingConfirmation, "Catastrophic failure! " + t);
            t.printStackTrace();

            // This usually means an empty incomprehensible response from the API.
            // We will show an error thingie then throw the user back to the starting screen.
            // They'll be at least as frustrated as I am, but what can I do?
            new AlertDialog.Builder(context)
                .setTitle(R.string.api_empty_response_header)
                .setMessage(R.string.api_empty_response_message)
                .setIcon(android.R.drawable.ic_delete)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                  bookingConfirmationProgressBar.setVisibility(View.GONE);
                  Intent restartBookingIntent = new Intent(getContext(), CreateBookingActivity.class);
                  restartBookingIntent.putExtras(getActivity().getIntent());
                  restartBookingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                  startActivity(restartBookingIntent);
                }).show();
          }
        });
  }

  private void onApiCallFinish(View view, BookingConfirmation bookingConfirmation) {
    Context context = getContext();

    // Set the booking number
    String bookingNumber = String.format("#%s", bookingConfirmation.getBookingDetails().getBookingNumber());
    bookingNumberTextView.setText(bookingNumber);

    // Set various booking information
    BookingConfirmationDetails bookingDetails = bookingConfirmation.getBookingDetails();
    dateTextView.setText(bookingDetails.getArrivalDate());
    arrivalTextView.setText(bookingDetails.getArrivalTime());
    departureTextView.setText(bookingDetails.getDepartTime());
    participantsTextView.setText(bookingConfirmation.getNumberOfParticipants());
    costTextView.setText(String.format("%s kr", bookingConfirmation.getSumTotalExclVat()));

    // Set the special request, or hide it if it's empty
    String specialRequest = bookingConfirmation.getSpecialRequest();
    if (specialRequest == null || specialRequest.trim().isEmpty()) {
      view.findViewById(R.id.specialRequestHeader).setVisibility(View.GONE);
      specialRequestTextView.setVisibility(View.GONE);
    } else {
      specialRequestTextView.setText(bookingConfirmation.getSpecialRequest());
    }

    // Add requested technologies
    List<IWantedToMakeAStringButFailed> technologies = bookingConfirmation.getBookedTechList();
    if (technologies.isEmpty()) {
      view.findViewById(R.id.requestedTechnologiesHeader).setVisibility(View.GONE);
      requestedTechnologyRecyclerView.setVisibility(View.GONE);
    } else {
      Log.i(
          Logging.BookingConfirmation,
          String.format("Adding requested technologies: %s", technologies)
      );
      requestedTechnologyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
      requestedTechnologyRecyclerView.setAdapter(
          new IWantedToMakeAStringButFailedRecyclerAdapter(context, technologies)
      );
    }

    // Add requested food and beverages
    // TODO Make better layout for adapter
    List<BookingConfirmationFoodBeverage> foodBeverages = bookingConfirmation.getBookedFoodBeverage();
    if (foodBeverages.isEmpty()) {
      view.findViewById(R.id.requestedFoodHeader).setVisibility(View.GONE);
      requestedFoodRecyclerView.setVisibility(View.GONE);
    } else {
      Log.i(
          Logging.BookingConfirmation,
          String.format("Adding requested foodBeverages: %s", foodBeverages)
      );
      requestedFoodRecyclerView.setLayoutManager(new LinearLayoutManager(context));
      requestedFoodRecyclerView.setAdapter(
          new BookingConfirmationFoodBeverageRecyclerAdapter(context, foodBeverages)
      );
    }

    // Set personal information fields
    BookingConfirmationBookedByPerson bcbbp = bookingConfirmation.getBookedByPerson();

    firstNameTextView.setText(bookingConfirmation.getMyFirstName());
    lastNameTextView.setText(bookingConfirmation.getMyLastName());
    phoneNumberTextView.setText(bookingConfirmation.getMyPhoneNumber());
    emailTextView.setText(bcbbp.getEmail());
    organizationNumberTextView.setText(bcbbp.getOrganizationNumber());
    organizationNameTextView.setText(bcbbp.getOrganizationName());

    // All done, shut down the progress bar!
    bookingConfirmationProgressBar.setVisibility(View.GONE);
  }
}