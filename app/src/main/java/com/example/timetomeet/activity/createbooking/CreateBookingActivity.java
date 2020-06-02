package com.example.timetomeet.activity.createbooking;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.entity.CitySimplified;
import com.example.timetomeet.retrofit.entity.FoodBeverage;
import com.example.timetomeet.retrofit.entity.Seating;
import com.example.timetomeet.retrofit.entity.Technology;

import java.util.Map;

public class CreateBookingActivity extends AppCompatActivity {
  private Bundle bookingBundle;
  private BookingCoordinator bookingCoordinator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_booking);
    Log.i(Logging.CreateBookingActivity, "Activity started");

    Bundle apiData = getIntent().getExtras();
    bookingBundle = new Bundle();
    bookingCoordinator = new BookingCoordinator(apiData);
  }

  public BookingCoordinator getBookingCoordinator() {
    return bookingCoordinator;
  }

  // TODO Delete once we've fully migrated to the bookingCoordinator
  public Bundle getBookingBundle() {
    return bookingBundle;
  }

  // TODO Delete once we've fully migrated to the bookingCoordinator
  public Map<Long, CitySimplified> getCityMap() {
    return bookingCoordinator.getCityMap();
  }

  // TODO Delete once we've fully migrated to the bookingCoordinator
  public Map<Long, Seating> getSeatingMap() {
    return bookingCoordinator.getSeatingMap();
  }

  // TODO Delete once we've fully migrated to the bookingCoordinator
  public Map<Long, Technology> getTechnologyMap() {
    return bookingCoordinator.getTechnologyMap();
  }

  // TODO Delete once we've fully migrated to the bookingCoordinator
  public Map<Long, FoodBeverage> getFoodMap() {
    return bookingCoordinator.getFoodMap();
  }
}
