package com.example.timetomeet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.timetomeet.Helper;
import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.example.timetomeet.activity.createbooking.CreateBookingActivity;
import com.example.timetomeet.retrofit.RetrofitHelper;
import com.example.timetomeet.retrofit.entity.Token;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
  private Button signInButton, signUpButton;
  private EditText usernameText, passwordText;
  private SharedPreferences userDetailPreferences;
  private Bundle apiData;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    Log.i(Logging.LoginActivity, "Activity started");

    // Unpack intents
    apiData = getIntent().getExtras();

    // Find views in activity
    signInButton = findViewById(R.id.signInButton);
    signUpButton = findViewById(R.id.signUpButton);
    usernameText = findViewById(R.id.editUsername);
    passwordText = findViewById(R.id.editPassword);

    // Retrieve saved username from shared preferences
    //userDetailPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    userDetailPreferences = getSharedPreferences(Helper.PREF_USER_DETAILS, MODE_PRIVATE);
    usernameText.setText(userDetailPreferences.getString(Helper.PREF_EMAIL, ""));
    passwordText.setText(userDetailPreferences.getString(Helper.PREF_PASSWORD, ""));

    // Bind buttons to methods
    signInButton.setOnClickListener(this::signInButtonClick);
    signUpButton.setOnClickListener(this::signUpButtonClick);
  }

  private void signInButtonClick(View view) {
    String email = usernameText.getText().toString();
    String username = "z_" + email;
    String password = passwordText.getText().toString();

    RetrofitHelper.signIn(username, password).enqueue(new Callback<Token>() {
      @Override
      public void onResponse(Call<Token> call, Response<Token> response) {
        Token token = response.body();
        if (token != null) {
          SharedPreferences.Editor spe = userDetailPreferences.edit();
          spe.putString(Helper.PREF_EMAIL, email);
          spe.putString(Helper.PREF_PASSWORD, password);
          spe.apply();
          apiData.putString(Helper.BUNDLE_TOKEN, String.format("Token %s", token.getToken()));
          startCreateBooking();

        } else {
          Log.i(Logging.LoginActivity, "Failed to log in");
          try {
            Log.i(Logging.LoginActivity, response.errorBody().string());
          } catch (IOException e) { e.printStackTrace(); }

          Snackbar.make(view, R.string.invalid_credentials, Snackbar.LENGTH_LONG).show();
        }
      }

      @Override
      public void onFailure(Call<Token> call, Throwable t) {
        Snackbar.make(view, R.string.something_went_wrong, Snackbar.LENGTH_LONG).show();
      }
    });
  }

  private void startCreateBooking() {
    Log.i(Logging.LoginActivity, "Creating intent to start MainMenuActivity.");
    Intent intent = new Intent(this, CreateBookingActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    intent.putExtras(apiData);
    startActivity(intent);
  }

  private void signUpButtonClick(View view) {
    Log.i(Logging.LoginActivity, "Creating intent to start NewUserActivity.");
    Intent intent = new Intent(this, NewUserActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    intent.putExtras(apiData);
    startActivity(intent);
  }
}
