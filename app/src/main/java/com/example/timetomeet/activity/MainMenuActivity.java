package com.example.timetomeet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.example.timetomeet.customview.DateDisplayListener;

public class MainMenuActivity extends AppCompatActivity {
  private Button createBookingButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_menu);
    Log.i(Logging.MainMenuActivity, "Activity started");

    createBookingButton = findViewById(R.id.createBookingButton);
    createBookingButton.setOnClickListener(this::startCreateBookingActivity);
  }

  private void startCreateBookingActivity(View view) {
    Intent intent = new Intent(this, CreateBookingActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
  }
}
