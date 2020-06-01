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
import com.example.timetomeet.retrofit.entity.FoodBeverageGroup;
import com.example.timetomeet.retrofit.entity.FoodBeverage;
import com.example.timetomeet.retrofit.entity.PaymentAlternative;
import com.example.timetomeet.retrofit.entity.Seating;
import com.example.timetomeet.retrofit.entity.Technology;
import com.example.timetomeet.retrofit.entity.TechnologyAvailability;

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
  private long startTime;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    Log.i(Logging.SplashActivity, "Activity started");

    progressBar = findViewById(R.id.progressBar);
    loadingTextView = findViewById(R.id.loadingTextView);

    apiData = new Bundle();
    startTime = System.currentTimeMillis() + 3000;

    loadContent();
    startLoginScreen();
  }

  /**
   * Fetch various things from the server, such as cities with venues,
   * various techs and so on.
   */
  private void loadContent() {
    // Set max value on progress bar to the number of items we're fetching.
    progressBar.setMax(7);

    // Fetch items
    fetchStandardSeating();
    fetchCities();
    fetchFoodBevarageGroupList();
    fetchFoodBevarageList();
    fetchTechnology();
    fetchTechnologyAvailabilityList();
    fetchPaymentAlternatives();
  }

  private void fetchStandardSeating() {
    ArrayList<Seating> standardSeating = new ArrayList<>();
    apiData.putParcelableArrayList(Helper.BUNDLE_STANDARD_SEATING, standardSeating);

    RetrofitHelper.getStandardSeating().enqueue(new Callback<List<Seating>>() {
      @Override
      public void onResponse(Call<List<Seating>> call, Response<List<Seating>> response) {
        if (response.body() != null) {
          standardSeating.addAll(response.body());
        }

        loadingTextView.setText(R.string.fetched_standard_seating);
        incrementProgressBar();
      }

      @Override
      public void onFailure(Call<List<Seating>> call, Throwable t) {
      }
    });
  }

  private void fetchTechnologyAvailabilityList() {
    ArrayList<TechnologyAvailability> technologyAvailabilityTexts = new ArrayList<>();
    apiData.putParcelableArrayList(Helper.BUNDLE_AVAILABLE_TECHNOLOGY_LIST, technologyAvailabilityTexts);

    RetrofitHelper.getTechonolyAvailability().enqueue(new Callback<List<TechnologyAvailability>>() {
      @Override
      public void onResponse(Call<List<TechnologyAvailability>> call, Response<List<TechnologyAvailability>> response) {
        if (response.body() != null) {
          technologyAvailabilityTexts.addAll(response.body());
        }

        loadingTextView.setText(R.string.fetched_technology_availability_text);
        incrementProgressBar();
      }

      @Override
      public void onFailure(Call<List<TechnologyAvailability>> call, Throwable t) {
      }
    });
  }

  private void fetchFoodBevarageList() {
    ArrayList<FoodBeverage> foodNDrinks = new ArrayList<>();
    apiData.putParcelableArrayList(Helper.BUNDLE_FOOD_BEVARAGE_LIST, foodNDrinks);

    RetrofitHelper.getFoodBevarageList().enqueue(new Callback<List<FoodBeverage>>() {
      @Override
      public void onResponse(Call<List<FoodBeverage>> call, Response<List<FoodBeverage>> response) {
        if (response.body() != null) {
          foodNDrinks.addAll(response.body());
        }

        loadingTextView.setText(R.string.fetched_food_bevarage_list);
        incrementProgressBar();
      }

      @Override
      public void onFailure(Call<List<FoodBeverage>> call, Throwable t) {
      }
    });
  }

  private void fetchFoodBevarageGroupList() {
    ArrayList<FoodBeverageGroup> mealGroups = new ArrayList<>();
    apiData.putParcelableArrayList(Helper.BUNDLE_FOOD_BEVARAGE_GROUP_LIST, mealGroups);

    RetrofitHelper.getFoodBevarageGroupList().enqueue(new Callback<List<FoodBeverageGroup>>() {
      @Override
      public void onResponse(Call<List<FoodBeverageGroup>> call, Response<List<FoodBeverageGroup>> response) {
        if (response.body() != null) {
          mealGroups.addAll(response.body());
        }

        loadingTextView.setText(R.string.fetched_food_bevarage_group_list);
        incrementProgressBar();
      }

      @Override
      public void onFailure(Call<List<FoodBeverageGroup>> call, Throwable t) {
      }
    });
  }

  private void fetchCities() {
    ArrayList<CitySimplified> cities = new ArrayList<>();
    apiData.putParcelableArrayList(Helper.BUNDLE_CITIES, cities);

    RetrofitHelper.getCitiesWithVenues().enqueue(new Callback<List<CitySimplified>>() {
      @Override
      public void onResponse(Call<List<CitySimplified>> call, Response<List<CitySimplified>> response) {
        if (response.body() != null) {
          cities.addAll(response.body());
        }

        loadingTextView.setText(R.string.fetched_cities);
        incrementProgressBar();
      }

      @Override
      public void onFailure(Call<List<CitySimplified>> call, Throwable t) {
      }
    });
  }

  private void fetchTechnology() {
    ArrayList<Technology> technologies = new ArrayList<>();
    apiData.putParcelableArrayList(Helper.BUNDLE_TECHNOLOGIES, technologies);

    RetrofitHelper.getTechnology().enqueue(new Callback<List<Technology>>() {
      @Override
      public void onResponse(Call<List<Technology>> call, Response<List<Technology>> response) {
        if (response.body() != null) {
          technologies.addAll(response.body());
        }

        loadingTextView.setText(R.string.fetched_technology);
        incrementProgressBar();
      }

      @Override
      public void onFailure(Call<List<Technology>> call, Throwable t) {

      }
    });
  }

  private void fetchPaymentAlternatives() {
    ArrayList<PaymentAlternative> paymentAlternatives = new ArrayList<>();
    apiData.putParcelableArrayList(Helper.BUNDLE_PAYMENT_ALTERNATIVES, paymentAlternatives);

    RetrofitHelper.getPaymentAlternatives().enqueue(new Callback<List<PaymentAlternative>>() {
      @Override
      public void onResponse(Call<List<PaymentAlternative>> call, Response<List<PaymentAlternative>> response) {
        if (response.body() != null) {
          paymentAlternatives.addAll(response.body());
        }

        loadingTextView.setText(R.string.fetched_payment_alternatives);
        incrementProgressBar();
      }

      @Override
      public void onFailure(Call<List<PaymentAlternative>> call, Throwable t) {
      }
    });
  }

  private void incrementProgressBar() {
    // Set the everything loaded text, and increase the startTime
    // if necessary to give the message time to show.
    if (progressBar.getProgress() == progressBar.getMax() - 1) {
      loadingTextView.setText(R.string.everything_loaded);
      Log.i(Logging.SplashActivity, "Load text is: " + loadingTextView.getText());

      long minimumStartTime = System.currentTimeMillis() + 250;
      startTime = Math.max(startTime, minimumStartTime);
    }

    // Increment the progress bar
    progressBar.incrementProgressBy(1);
  }

  /**
   * Create intent for login activity and start it at the specified time.
   */
  private void startLoginScreen() {
    Log.i(Logging.SplashActivity, "Creating intent to start LoginActivity.");
    Intent intent = new Intent(this, LoginActivity.class);
    startActivityFromIntent(intent);
  }

  /**
   * Starts the specified intent at the time specified by startTime.
   * The reason for this is that if we want to go to a screen other than the
   * login activity we're free to do so.
   * @param intent The intent to be started.
   */
  private void startActivityFromIntent(final Intent intent) {
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    long retryDelay = 100;

    Handler activityStartHandler = new Handler();

    activityStarter = () -> {
      long startIn = startTime - System.currentTimeMillis();
      boolean startTimeHasPassed = startIn < 1;
      boolean allTasksFinished = progressBar.getMax() <= progressBar.getProgress();

      Log.i(Logging.SplashActivity, String.format("Starting activity in: %s", startIn));
      if (startTimeHasPassed && allTasksFinished) {
        intent.putExtras(apiData);
        startActivity(intent);

      } else {
        activityStartHandler.postDelayed(activityStarter, retryDelay);
      }
    };
    activityStartHandler.postDelayed(activityStarter, retryDelay);
  }
}
