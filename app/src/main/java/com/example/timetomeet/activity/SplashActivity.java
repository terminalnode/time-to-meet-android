package com.example.timetomeet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.timetomeet.Helper;
import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.RetrofitHelper;
import com.example.timetomeet.retrofit.entity.CitySimplified;
import com.example.timetomeet.retrofit.entity.PaymentAlternative;
import com.example.timetomeet.retrofit.entity.Technology;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
  private ProgressBar progressBar;
  private TextView loadingTextView;
  private Runnable activityStarter;
  private Bundle apiData;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    Log.i(Logging.SplashActivity, "Activity started");

    progressBar = findViewById(R.id.progressBar);
    loadingTextView = findViewById(R.id.loadingTextView);

    apiData = new Bundle();
    loadContent();

    long startTime = System.currentTimeMillis() + 4000;
    startLoginScreen(startTime);
  }

  /**
   * Fetch various things from the server, such as cities with venues,
   * various techs and so on.
   */
  private void loadContent() {
    // Set max value on progress bar to the number of items we're fetching.
    progressBar.setMax(3);

    // Fetch items
    fetchCities();
    fetchTechnology();
    fetchPaymentAlternatives();
  }

  private void fetchCities() {
    loadingTextView.setText(R.string.fetching_cities);
    ArrayList<CitySimplified> cities = new ArrayList<>();
    apiData.putParcelableArrayList(Helper.BUNDLE_CITIES, cities);

    RetrofitHelper.getCitiesWithVenues().enqueue(new Callback<List<CitySimplified>>() {
      @Override
      public void onResponse(Call<List<CitySimplified>> call, Response<List<CitySimplified>> response) {
        if (response.body() != null) {
          cities.addAll(response.body());
        }

        incrementProgressBar();
        loadingTextView.setText(R.string.fetched_cities);
      }

      @Override
      public void onFailure(Call<List<CitySimplified>> call, Throwable t) {
      }
    });
  }

  private void fetchTechnology() {
    loadingTextView.setText(R.string.fetching_technology);
    ArrayList<Technology> technologies = new ArrayList<>();
    apiData.putParcelableArrayList(Helper.BUNDLE_TECHNOLOGIES, technologies);

    RetrofitHelper.getTechnology().enqueue(new Callback<List<Technology>>() {
      @Override
      public void onResponse(Call<List<Technology>> call, Response<List<Technology>> response) {
        if (response.body() != null) {
          technologies.addAll(response.body());
        }

        incrementProgressBar();
        loadingTextView.setText(R.string.fetched_technology);
      }

      @Override
      public void onFailure(Call<List<Technology>> call, Throwable t) {

      }
    });
  }

  private void fetchPaymentAlternatives() {
    loadingTextView.setText(R.string.fetching_payment_alternatives);
    ArrayList<PaymentAlternative> paymentAlternatives = new ArrayList<>();
    apiData.putParcelableArrayList(Helper.BUNDLE_PAYMENT_ALTERNATIVES, paymentAlternatives);

    RetrofitHelper.getPaymentAlternatives().enqueue(new Callback<List<PaymentAlternative>>() {
      @Override
      public void onResponse(Call<List<PaymentAlternative>> call, Response<List<PaymentAlternative>> response) {
        if (response.body() != null) {
          paymentAlternatives.addAll(response.body());
        }

        incrementProgressBar();
        loadingTextView.setText(R.string.fetched_payment_alternatives);
      }

      @Override
      public void onFailure(Call<List<PaymentAlternative>> call, Throwable t) {
      }
    });
  }

  private void incrementProgressBar() {
    progressBar.incrementProgressBy(1);
    if (progressBar.getProgress() == progressBar.getMax()) {
      loadingTextView.setText(R.string.everything_loaded);
    }
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

    Handler activityStartHandler = new Handler();

    activityStarter = () -> {
      long startIn = startTime - System.currentTimeMillis();
      boolean startTimeHasPassed = startIn < 1;
      boolean allTasksFinished = progressBar.getMax() >= progressBar.getProgress();

      Log.i(Logging.SplashActivity, String.format("Starting activity in: %s", startIn));
      if (startTimeHasPassed && allTasksFinished) {
        intent.putExtras(apiData);
        startActivity(intent);

      } else {
        activityStartHandler.postDelayed(activityStarter, 250);
      }
    };
    activityStartHandler.postDelayed(activityStarter, 250);
  }
}
