package com.example.timetomeet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.example.timetomeet.customview.DateDisplayListener;

public class MainMenuActivity extends AppCompatActivity {
  private ConstraintLayout startDateDisplay;
  private ConstraintLayout endDateDisplay;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_menu);
    Log.i(Logging.MainMenuActivity, "Activity started");

    startDateDisplay = findViewById(R.id.startDateDisplay);
    endDateDisplay = findViewById(R.id.endDateDisplay);

    TextView startDateText = startDateDisplay.findViewById(R.id.dateTextView);
    startDateText.setText(R.string.pick_start_date);

    TextView endDateText = endDateDisplay.findViewById(R.id.dateTextView);
    endDateText.setText(R.string.pick_end_date);

    startDateDisplay.setOnClickListener(new DateDisplayListener(this, startDateText));
    endDateDisplay.setOnClickListener(new DateDisplayListener(this, endDateText));
  }
}
