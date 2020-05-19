package com.example.timetomeet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.RetrofitHelper;
import com.example.timetomeet.retrofit.entity.Token;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
  private static String usernamePref = "login-activity-username";

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
    usernameText.setText(sharedPreferences.getString(usernamePref, ""));

    // Bind buttons to methods
    signInButton.setOnClickListener(this::signInButtonClick);
    signUpButton.setOnClickListener(this::signUpButtonClick);
  }

  private void signInButtonClick(View view) {
    String username = usernameText.getText().toString();
    String password = passwordText.getText().toString();

    RetrofitHelper.signIn(username, password).enqueue(new Callback<Token>() {
      @Override
      public void onResponse(Call<Token> call, Response<Token> response) {
        Token token = response.body();
        if (token != null) {
          SharedPreferences.Editor spe = sharedPreferences.edit();
          spe.putString(usernamePref, username);
          spe.apply();

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

  private void signUpButtonClick(View view) {
    Snackbar.make(view, "Signing up!", Snackbar.LENGTH_LONG).show();
  }
}
