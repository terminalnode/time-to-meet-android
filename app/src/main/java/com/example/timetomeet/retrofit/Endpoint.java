package com.example.timetomeet.retrofit;

import com.example.timetomeet.retrofit.entity.LoginCredentials;
import com.example.timetomeet.retrofit.entity.Token;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Endpoint {
  @POST("api-token-auth")
  Call<Token> login(@Body LoginCredentials loginCredentials);
}
