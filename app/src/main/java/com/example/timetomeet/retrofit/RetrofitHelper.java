package com.example.timetomeet.retrofit;

import com.example.timetomeet.retrofit.entity.availableroom.AvailableRoomsContainer;
import com.example.timetomeet.retrofit.entity.availableroom.AvailableRoomsQuery;
import com.example.timetomeet.retrofit.entity.BookingAdd;
import com.example.timetomeet.retrofit.entity.BookingFoodBeverageAdd;
import com.example.timetomeet.retrofit.entity.BookingSelectableTechnologyAdd;
import com.example.timetomeet.retrofit.entity.City;
import com.example.timetomeet.retrofit.entity.CitySimplified;
import com.example.timetomeet.retrofit.entity.bookingconfirmation.BookingConfirmation;
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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
  private static final String BASE_URL = "https://dev-be.timetomeet.se/service/rest/";

  private static Endpoint getBase() {
    return new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Endpoint.class);
  }

  public static Call<Token> signIn(String username, String password) {
    Credentials credentials = new Credentials();
    credentials.setUsername(username);
    credentials.setPassword(password);
    return getBase().signIn(credentials);
  }

  public static Call<User> signUp(User user) {
    return getBase().signUp(user);
  }

  public static Call<City> getCityById(long id) {
    return getBase().getCityById(id);
  }

  public static Call<ConferenceRoom> getConferenceRoomById(long id) {
    return getBase().getConferenceRoomById(id);
  }

  public static Call<Venue> getVenueById(long id) {
    return getBase().getVenueById(id);
  }

  public static Call<List<PaymentAlternative>> getPaymentAlternatives() {
    return getBase().getPaymentAlternatives();
  }

  public static Call<List<Technology>> getTechnology() {
    return getBase().getTechnology();
  }

  public static Call<AvailableRoomsContainer> searchAvailableRoomsByCity(AvailableRoomsQuery query) {
    query.setTypeCity();
    return getBase().searchAvailableRoomsByCity(query);
  }

  public static Call<AvailableRoomsContainer> searchAvailableRoomsByPlant(AvailableRoomsQuery query) {
    query.setTypePlant();
    return getBase().searchAvailableRoomsByCity(query);
  }

  public static Call<AvailableRoomsContainer> searchAvailableRoomsByOrganization(AvailableRoomsQuery query) {
    query.setTypeOrganization();
    return getBase().searchAvailableRoomsByCity(query);
  }

  public static Call<AvailableRoomsContainer> searchAvailableRoomsByRoom(AvailableRoomsQuery query) {
    query.setTypeRoom();
    return getBase().searchAvailableRoomsByCity(query);
  }

  public static Call<List<CitySimplified>> getCitiesWithVenues() {
    return getBase().getCitiesWithVenues();
  }

  public static Call<List<TechnologyAvailability>> getTechonolyAvailability() {
    return getBase().getTechnologyAvailability();
  }

  public static Call<List<FoodBeverageGroup>> getFoodBevarageGroupList() {
    return getBase().getFoodBevarageGroupList();
  }

  public static Call<List<FoodBeverage>> getFoodBevarageList() {
    return getBase().getFoodBevarageList();
  }

  public static Call<List<ConferenceRoomTechnology>> getConferenceRoomTechnology(long roomId) {
    return getBase().getConferenceRoomTechnology(roomId);
  }

  public static Call<List<Seating>> getStandardSeating() {
    return getBase().getStandardSeating();
  }

  public static Call<BookingAdd> addBooking(BookingAdd addBooking, String token) {
    return getBase().addBooking(addBooking, token);
  }

  public static Call<TimeSlotAdd> addTimeSlot(TimeSlotAdd timeSlotAdd, Long timeSlotId, String token) {
    return getBase().addTimeSlot(timeSlotAdd, timeSlotId, token);
  }

  public static Call<List<VenueFoodBeverage>> getPlantFoodBeverage(Long id) {
    return getBase().getFoodBeverageByPlant(id);
  }

  public static Call<BookingFoodBeverageAdd> addFoodBeverage(
      String token, BookingFoodBeverageAdd bfba) {
    return getBase().addFoodBeverage(token, bfba);
  }

  public static Call<BookingSelectableTechnologyAdd> addSelectableTechnology(
      String token, BookingSelectableTechnologyAdd bsta) {
    return getBase().addSelectableTechnology(token, bsta);
  }

  public static Call<BookingConfirmation> finalizeBooking(String token) {
    return getBase().finalizeBooking(token);
  }
}
