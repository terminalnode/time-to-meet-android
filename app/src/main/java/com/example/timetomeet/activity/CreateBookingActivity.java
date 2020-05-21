package com.example.timetomeet.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timetomeet.Logging;
import com.example.timetomeet.R;

public class CreateBookingActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_booking);
    Log.i(Logging.CreateBookingActivity, "Activity started");

  }

}
