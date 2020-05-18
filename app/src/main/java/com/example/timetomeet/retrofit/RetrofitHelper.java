package com.example.timetomeet.retrofit;

import com.example.timetomeet.retrofit.entity.LoginCredentials;
import com.example.timetomeet.retrofit.entity.Token;

import retrofit2.Call;
import retrofit2.Retrofit;

public class RetrofitHelper {
  private static final String BASE_URL = "https://dev-be.timetomeet.se/service/rest/";

  private static Endpoint getBase() {
    return new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .build()
        .create(Endpoint.class);
  }

  public static Call<Token> login(LoginCredentials loginCredentials) {
    return getBase().login(loginCredentials);
  }
}
