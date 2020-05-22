package com.example.timetomeet.retrofit;

import com.example.timetomeet.retrofit.entity.AvailableRoomsContainer;
import com.example.timetomeet.retrofit.entity.AvailableRoomsQuery;
import com.example.timetomeet.retrofit.entity.City;
import com.example.timetomeet.retrofit.entity.CitySimplified;
import com.example.timetomeet.retrofit.entity.Credentials;
import com.example.timetomeet.retrofit.entity.PaymentAlternative;
import com.example.timetomeet.retrofit.entity.Technology;
import com.example.timetomeet.retrofit.entity.TechnologyAvailability;
import com.example.timetomeet.retrofit.entity.Token;
import com.example.timetomeet.retrofit.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Endpoint {
  @POST("api-token-auth/")
  Call<Token> signIn(@Body Credentials credentials);

  @POST("user/add/")
  Call<User> signUp(@Body User user);

  @GET("city/{cityId}/")
  Call<City> getCityById(@Path("cityId") long cityId);

  @GET("paymentalternative/")
  Call<List<PaymentAlternative>> getPaymentAlternatives();

  @GET("technology/")
  Call<List<Technology>> getTechnology();

  @POST("search/availability/period/v3")
  Call<AvailableRoomsContainer> searchAvailableRoomsByCity(@Body AvailableRoomsQuery query);

  @GET("citieswithvenues/")
  Call<List<CitySimplified>> getCitiesWithVenues();

  @GET("technologyavailability/confereceroom/{id}/")
  Call<List<TechnologyAvailability>> getTechnologyAvailability(@Path("id") long roomId);
}
