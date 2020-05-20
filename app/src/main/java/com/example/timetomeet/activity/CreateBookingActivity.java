package com.example.timetomeet.activity;

import android.os.Bundle;

import com.example.timetomeet.Logging;
import com.example.timetomeet.customview.DateDisplayListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.timetomeet.R;

public class CreateBookingActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_booking);
    Log.i(Logging.CreateBookingActivity, "Activity started");

  }

}
