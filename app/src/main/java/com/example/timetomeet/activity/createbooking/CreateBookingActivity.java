package com.example.timetomeet.activity.createbooking;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timetomeet.Helper;
import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.entity.CitySimplified;
import com.example.timetomeet.retrofit.entity.FoodBeverage;
import com.example.timetomeet.retrofit.entity.Seating;
import com.example.timetomeet.retrofit.entity.Technology;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CreateBookingActivity extends AppCompatActivity {
  private Bundle bookingBundle;
  private Map<Long, CitySimplified> cityMap;
  private Map<Long, Seating> seatingMap;
  private Map<Long, Technology> technologyMap;
  private Map<Long, FoodBeverage> foodMap;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_booking);
    Log.i(Logging.CreateBookingActivity, "Activity started");

    Bundle apiData = getIntent().getExtras();
    bookingBundle = new Bundle();

    // Create map of id -> citySimplified
    List<CitySimplified> cities = apiData.getParcelableArrayList(Helper.BUNDLE_CITIES);
    cityMap = cities.stream()
        .collect(Collectors.toMap(
            CitySimplified::getId, // Key
            citySimplified -> citySimplified // Value
        ));

    // Create map of id -> seating
    List<Seating> seatings = apiData.getParcelableArrayList(Helper.BUNDLE_STANDARD_SEATING);
    seatingMap = seatings.stream()
        .collect(Collectors.toMap(
            Seating::getId, // Key
            seating -> seating // Value
        ));

    // Create map of id -> technology
    List<Technology> technologies = apiData.getParcelableArrayList(Helper.BUNDLE_TECHNOLOGIES);
    technologyMap = technologies.stream()
        .collect(Collectors.toMap(
            Technology::getId, // Key
            technology -> technology // Value
        ));

    // Create map of id -> foodbeverage
    List<FoodBeverage> foodBeverages = apiData.getParcelableArrayList(Helper.BUNDLE_FOOD_BEVARAGE_LIST);
    foodMap = foodBeverages.stream()
        .collect(Collectors.toMap(
            FoodBeverage::getId,
            foodBeverage -> foodBeverage
        ));
  }

  public Bundle getBookingBundle() {
    return bookingBundle;
  }

  public Map<Long, CitySimplified> getCityMap() {
    return cityMap;
  }

  public Map<Long, Seating> getSeatingMap() {
    return seatingMap;
  }

  public Map<Long, Technology> getTechnologyMap() {
    return technologyMap;
  }

  public Map<Long, FoodBeverage> getFoodMap() {
    return foodMap;
  }
}
