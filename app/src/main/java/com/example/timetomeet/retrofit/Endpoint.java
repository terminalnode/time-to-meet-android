package com.example.timetomeet.retrofit;

import com.example.timetomeet.retrofit.entity.availableroom.AvailableRoomsContainer;
import com.example.timetomeet.retrofit.entity.availableroom.AvailableRoomsQuery;
import com.example.timetomeet.retrofit.entity.BookingAdd;
import com.example.timetomeet.retrofit.entity.BookingFoodBeverageAdd;
import com.example.timetomeet.retrofit.entity.BookingSelectableTechnologyAdd;
import com.example.timetomeet.retrofit.entity.City;
import com.example.timetomeet.retrofit.entity.CitySimplified;
import com.example.timetomeet.retrofit.entity.conferenceroom.ConferenceRoom;
import com.example.timetomeet.retrofit.entity.conferenceroom.ConferenceRoomTechnology;
import com.example.timetomeet.retrofit.entity.Credentials;
import com.example.timetomeet.retrofit.entity.FoodBeverageGroup;
import com.example.timetomeet.retrofit.entity.FoodBeverage;
import com.example.timetomeet.retrofit.entity.PaymentAlternative;
import com.example.timetomeet.retrofit.entity.Seating;
import com.example.timetomeet.retrofit.entity.Technology;
import com.example.timetomeet.retrofit.entity.TechnologyAvailability;
import com.example.timetomeet.retrofit.entity.TimeSlotAdd;
import com.example.timetomeet.retrofit.entity.Token;
import com.example.timetomeet.retrofit.entity.User;
import com.example.timetomeet.retrofit.entity.Venue;
import com.example.timetomeet.retrofit.entity.VenueFoodBeverage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Endpoint {
  @POST("api-token-auth/")
  Call<Token> signIn(
      @Body Credentials credentials);

  @POST("user/add/")
  Call<User> signUp(
      @Body User user);

  @GET("city/{id}/")
  Call<City> getCityById(
      @Path("id") long cityId);

  @GET("conferenceroom/{id}/")
  Call<ConferenceRoom> getConferenceRoomById(
      @Path("id") long id);

  @GET("venue/{id}/")
  Call<Venue> getVenueById(
      @Path("id") long id);

  @GET("paymentalternative/")
  Call<List<PaymentAlternative>> getPaymentAlternatives();

  @GET("technology/")
  Call<List<Technology>> getTechnology();

  @POST("search/availability/period/v3")
  Call<AvailableRoomsContainer> searchAvailableRoomsByCity(
      @Body AvailableRoomsQuery query);

  @GET("citieswithvenues/")
  Call<List<CitySimplified>> getCitiesWithVenues();

  @GET("technologyavailability/")
  Call<List<TechnologyAvailability>> getTechnologyAvailability();

  @GET("foodbeveragegroup/")
  Call<List<FoodBeverageGroup>> getFoodBevarageGroupList();

  @GET("foodbeverage/")
  Call<List<FoodBeverage>> getFoodBevarageList();

  @GET("conferenceroomtechnology/conferenceroom/{id}/")
  Call<List<ConferenceRoomTechnology>> getConferenceRoomTechnology(
      @Path("id") long roomId);

  @GET("standardseating/")
  Call<List<Seating>> getStandardSeating();

  @POST("booking/add/")
  Call<BookingAdd> addBooking(
      @Body BookingAdd addBooking,
      @Header("Authorization") String token);

  @PUT("conferenceroomavailability/book/{id}/")
  Call<TimeSlotAdd> addTimeSlot(
      @Body TimeSlotAdd timeSlotAdd,
      @Path("id") Long timeSlotId,
      @Header("Authorization") String token);

  @GET("plantfoodbeverage/venue/{id}/")
  Call<List<VenueFoodBeverage>> getFoodBeverageByPlant(
      @Path("id") Long id
  );

  @POST("bookingfoodbeverage/add/")
  Call<BookingFoodBeverageAdd> addFoodBeverage(
      @Header("Authorization") String token,
      @Body BookingFoodBeverageAdd bookingFoodBeverageAdd
  );

  @POST("bookingselectabletechnology/add/")
  Call<BookingSelectableTechnologyAdd> addSelectableTechnology(
      @Header("Authorization") String token,
      @Body BookingSelectableTechnologyAdd bookingSelectableTechnologyAdd
  );
}
