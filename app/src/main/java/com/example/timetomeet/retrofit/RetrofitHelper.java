package com.example.timetomeet.retrofit;

import com.example.timetomeet.retrofit.entity.AvailableRoomsContainer;
import com.example.timetomeet.retrofit.entity.AvailableRoomsQuery;
import com.example.timetomeet.retrofit.entity.City;
import com.example.timetomeet.retrofit.entity.Credentials;
import com.example.timetomeet.retrofit.entity.PaymentAlternative;
import com.example.timetomeet.retrofit.entity.Technology;
import com.example.timetomeet.retrofit.entity.Token;
import com.example.timetomeet.retrofit.entity.User;

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

  public static Call<City> getCityById(int cityId) {
    return getBase().getCityById(cityId);
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
}
