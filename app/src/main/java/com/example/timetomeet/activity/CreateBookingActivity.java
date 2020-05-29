package com.example.timetomeet.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timetomeet.Helper;
import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.entity.CitySimplified;
import com.example.timetomeet.retrofit.entity.PaymentAlternative;
import com.example.timetomeet.retrofit.entity.Seating;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CreateBookingActivity extends AppCompatActivity {
  private Bundle bookingBundle;
  private Map<Long, CitySimplified> cityMap;
  private Map<Long, Seating> seatingMap;
  private Map<Long, PaymentAlternative> paymentAlternativeMap;

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

    // Create map of id -> paymentalternative
    List<PaymentAlternative> paymentAlternatives = apiData.getParcelableArrayList(Helper.BUNDLE_PAYMENT_ALTERNATIVES);
    paymentAlternativeMap = paymentAlternatives.stream()
        .collect(Collectors.toMap(
            PaymentAlternative::getId, // Key
            paymentAlternative -> paymentAlternative // Value
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

  public Map<Long, PaymentAlternative> getPaymentAlternativeMap() {
    return paymentAlternativeMap;
  }
}
