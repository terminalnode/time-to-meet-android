package com.example.timetomeet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.timetomeet.Helper;
import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.RetrofitHelper;
import com.example.timetomeet.retrofit.entity.User;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewUserActivity extends AppCompatActivity {
  private Button registerUserButton;
  private ProgressBar progressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_user);
    Log.i(Logging.NewUserActivity, "Activity started");

    progressBar = findViewById(R.id.progressBar);
    registerUserButton = findViewById(R.id.registerUserButton);
    registerUserButton.setOnClickListener(this::registerUser);
  }

  private void registerUser(View view) {
    disableButton();

    String firstName = ((EditText) findViewById(R.id.firstNameEditText)).getText().toString().trim();
    String lastName = ((EditText) findViewById(R.id.lastNameEditText)).getText().toString().trim();
    String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();
    String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString().trim();
    String phoneNumber = ((EditText) findViewById(R.id.phoneNumberEditText)).getText().toString().trim();
    String orgName = ((EditText) findViewById(R.id.organizationNameEditText)).getText().toString().trim();
    String orgNumber = ((EditText) findViewById(R.id.organizationNumberEditText)).getText().toString().trim();
    String streetName = ((EditText) findViewById(R.id.streetAddressEditText)).getText().toString().trim();
    String cityName = ((EditText) findViewById(R.id.cityNameEditText)).getText().toString().trim();
    String zipCode = ((EditText) findViewById(R.id.zipCodeEditText)).getText().toString().trim();

    if (firstName.isEmpty()) {
      somethingWentWrongSnackbar(view);
      return;
    } else if (lastName.isEmpty()) {
      somethingWentWrongSnackbar(view);
      return;
    } else if (password.isEmpty()) {
      somethingWentWrongSnackbar(view);
      return;
    } else if (email.isEmpty()) {
      somethingWentWrongSnackbar(view);
      return;
    } else if (phoneNumber.isEmpty()) {
      somethingWentWrongSnackbar(view);
      return;
    } else if (orgName.isEmpty()) {
      somethingWentWrongSnackbar(view);
      return;
    } else if (orgNumber.isEmpty()) {
      somethingWentWrongSnackbar(view);
      return;
    } else if (streetName.isEmpty()) {
      somethingWentWrongSnackbar(view);
      return;
    } else if (cityName.isEmpty()) {
      somethingWentWrongSnackbar(view);
      return;
    } else if (zipCode.isEmpty()) {
      somethingWentWrongSnackbar(view);
      return;
    }

    User user = new User();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setPassword(password);
    user.setUsername("z_" + email);
    user.setEmail(email);
    user.setPhoneNumber(phoneNumber);
    user.setOrganizationName(orgName);
    user.setOrganizaionNumber(orgNumber);
    user.setStreet(streetName);
    user.setCityName(cityName);
    user.setZipCode(zipCode);

    RetrofitHelper
        .signUp(user)
        .enqueue(new Callback<User>() {
          @Override
          public void onResponse(Call<User> call, Response<User> response) {
            if (response.body() == null) {
              // We got some kind of error, god knows what because
              // the API documentation ain't tellin' us shit.
              try {
                Log.e(Logging.NewUserActivity, "Failed to register, got response: " + response.errorBody().string());
              } catch (IOException ignored) { }

              somethingWentWrongSnackbar(view);
              enableButton();

            } else {
              // Save newly registered username and password to preferences,
              // so that they can be filled in automatically on login.
              SharedPreferences.Editor prefEditor =
                  getSharedPreferences(Helper.PREF_USER_DETAILS, MODE_PRIVATE)
                      .edit();
              prefEditor.putString(Helper.PREF_EMAIL, email);
              prefEditor.putString(Helper.PREF_PASSWORD, password);
              prefEditor.apply();

              goBackToLoginScreen();
            }
          }

          @Override
          public void onFailure(Call<User> call, Throwable t) {
            Log.e(Logging.NewUserActivity, "Registration failed! ");
            t.printStackTrace();
            somethingWentWrongSnackbar(view);
            enableButton();
          }
        });
  }

  private void disableButton() {
    progressBar.setVisibility(View.VISIBLE);
    registerUserButton.setAlpha(0.5F);
    registerUserButton.setClickable(false);
  }

  private void enableButton() {
    progressBar.setVisibility(View.GONE);
    registerUserButton.setAlpha(1F);
    registerUserButton.setClickable(true);
  }

  private void somethingWentWrongSnackbar(View view) {
    Snackbar.make(
        view, R.string.something_went_wrong,
        Snackbar.LENGTH_LONG
    ).show();
  }

  private void goBackToLoginScreen() {
    Log.i(Logging.LoginActivity, "Creating intent to start LoginActivity.");
    Intent intent = new Intent(this, LoginActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    intent.putExtras(getIntent());
    startActivity(intent);
  }
}
