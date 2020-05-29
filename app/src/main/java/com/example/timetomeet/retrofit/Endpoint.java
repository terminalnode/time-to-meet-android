package com.example.timetomeet.retrofit;

import com.example.timetomeet.retrofit.entity.AvailableRoomsContainer;
import com.example.timetomeet.retrofit.entity.AvailableRoomsQuery;
import com.example.timetomeet.retrofit.entity.BookingAdd;
import com.example.timetomeet.retrofit.entity.City;
import com.example.timetomeet.retrofit.entity.CitySimplified;
import com.example.timetomeet.retrofit.entity.ConferenceRoom;
import com.example.timetomeet.retrofit.entity.ConferenceRoomTechnology;
import com.example.timetomeet.retrofit.entity.Credentials;
import com.example.timetomeet.retrofit.entity.FoodBevarageGroupList;
import com.example.timetomeet.retrofit.entity.FoodBevarageList;
import com.example.timetomeet.retrofit.entity.PaymentAlternative;
import com.example.timetomeet.retrofit.entity.Seating;
import com.example.timetomeet.retrofit.entity.Technology;
import com.example.timetomeet.retrofit.entity.TechnologyAvailability;
import com.example.timetomeet.retrofit.entity.Token;
import com.example.timetomeet.retrofit.entity.User;
import com.example.timetomeet.retrofit.entity.Venue;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Endpoint {
  @POST("api-token-auth/")
  Call<Token> signIn(@Body Credentials credentials);

  @POST("user/add/")
  Call<User> signUp(@Body User user);

  @GET("city/{id}/")
  Call<City> getCityById(@Path("id") long cityId);

  @GET("conferenceroom/{id}/")
  Call<ConferenceRoom> getConferenceRoomById(@Path("id") long id);

  @GET("venue/{id}/")
  Call<Venue> getVenueById(@Path("id") long id);

  @GET("paymentalternative/")
  Call<List<PaymentAlternative>> getPaymentAlternatives();

  @GET("technology/")
  Call<List<Technology>> getTechnology();

  @POST("search/availability/period/v3")
  Call<AvailableRoomsContainer> searchAvailableRoomsByCity(@Body AvailableRoomsQuery query);

  @GET("citieswithvenues/")
  Call<List<CitySimplified>> getCitiesWithVenues();

  @GET("technologyavailability/")
  Call<List<TechnologyAvailability>> getTechnologyAvailability();

  @GET("foodbeveragegroup/")
  Call<List<FoodBevarageGroupList>> getFoodBevarageGroupList();

  @GET("foodbeverage/")
  Call<List<FoodBevarageList>> getFoodBevarageList();

  @GET("conferenceroomtechnology/conferenceroom/{id}/")
  Call<List<ConferenceRoomTechnology>> getConferenceRoomTechnology(@Path("id") long roomId);

  @GET("standardseating/")
  Call<List<Seating>> getStandardSeating();

  @POST("booking/add/")
  Call<JSONObject> addBooking(@Body BookingAdd addBooking, @Header("Authorization") String token);
}
