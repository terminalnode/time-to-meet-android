package com.example.timetomeet.retrofit;

import com.example.timetomeet.retrofit.entity.Credentials;
import com.example.timetomeet.retrofit.entity.Token;
import com.example.timetomeet.retrofit.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Endpoint {
  @POST("api-token-auth/")
  Call<Token> signIn(@Body Credentials credentials);

  @POST("user/add/")
  Call<User> signUp(@Body User user);
}
