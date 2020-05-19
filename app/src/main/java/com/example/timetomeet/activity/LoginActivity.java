package com.example.timetomeet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {
  private Button signInButton, signUpButton;
  private EditText usernameText, passwordText;
  private SharedPreferences sharedPreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    Log.i(Logging.LoginActivity, "Activity started");

    // Find views in activity
    signInButton = findViewById(R.id.signInButton);
    signUpButton = findViewById(R.id.signUpButton);
    usernameText = findViewById(R.id.editUsername);
    passwordText = findViewById(R.id.editPassword);

    // Retrieve saved username from shared preferences
    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    usernameText.setText(sharedPreferences.getString("login-activity-username", ""));

    // Bind buttons to methods
    signInButton.setOnClickListener(this::signInButtonClick);
    signUpButton.setOnClickListener(this::signUpButtonClick);
  }

  private void signInButtonClick(View view) {
    Snackbar.make(view, "Signing in!", Snackbar.LENGTH_LONG).show();
    String username = usernameText.getText().toString();
    String password = passwordText.getText().toString();
  }

  private void signUpButtonClick(View view) {
    Snackbar.make(view, "Signing up!", Snackbar.LENGTH_LONG).show();
  }
}
