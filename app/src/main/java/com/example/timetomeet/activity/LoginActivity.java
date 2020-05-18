package com.example.timetomeet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.timetomeet.Logging;
import com.example.timetomeet.R;

public class LoginActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    Log.i(Logging.LoginActivity, "Activity started");
  }
}
