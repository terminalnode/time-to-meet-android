package com.example.timetomeet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.timetomeet.Logging;
import com.example.timetomeet.R;

public class SplashActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    Log.i(Logging.SplashActivity, "Activity started");

    long startTime = System.currentTimeMillis() + 4000;
    startLoginScreen(startTime);
  }

  /**
   * Create intent for login activity and start it at the specified time.
   * @param startTime Time to start the activity.
   */
  private void startLoginScreen(long startTime) {
    Log.i(Logging.SplashActivity, "Creating intent to start LoginActivity.");
    Intent intent = new Intent(this, LoginActivity.class);
    startActivityAtTime(intent, startTime);
  }

  /**
   * Starts the specified intent at a given time.
   * The reason for this is that if we want to go to a screen other than the
   * login activity we're free to do so.
   * @param intent The intent to be started.
   * @param startTime The time at which it should start.
   */
  private void startActivityAtTime(final Intent intent, long startTime) {
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    long startIn = startTime - System.currentTimeMillis();

    if (startIn < 1) {
      Log.i(Logging.SplashActivity, "Start time already passed, starting activity.");
      startActivity(intent);
    } else {
      Log.i(Logging.SplashActivity, String.format("Starting activity in: %s", startIn));
      new Handler().postDelayed(() -> {
        Log.i(Logging.SplashActivity, "Starting next activity.");
        startActivity(intent);
      }, startIn);
    }
  }
}
