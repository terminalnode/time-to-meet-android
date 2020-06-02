package com.example.timetomeet.activity.createbooking;

import android.os.Bundle;
import android.util.Log;

import com.example.timetomeet.Helper;
import com.example.timetomeet.Logging;
import com.example.timetomeet.retrofit.entity.CitySimplified;
import com.example.timetomeet.retrofit.entity.FoodBeverage;
import com.example.timetomeet.retrofit.entity.PaymentAlternative;
import com.example.timetomeet.retrofit.entity.Seating;
import com.example.timetomeet.retrofit.entity.Technology;
import com.example.timetomeet.retrofit.entity.availableroom.AvailableRoom;
import com.example.timetomeet.retrofit.entity.conferenceroom.ConferenceRoomSeating;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The purpose of this class is to hold the data accumulated throughout the booking process,
 * because the entire booking process takes place within a single activity (the
 * CreateBookingActivity), data can be passed between fragments without having to be parceled.
 * The data can be stored within the activity and then accessed from all of the fragment without
 * the use of intents or bundles.
 */
public class BookingCoordinator {
  // API data
  List<CitySimplified> citiesWithVenues;
  List<Seating> seatings;
  List<Technology> technologies;
  List<FoodBeverage> foodBeverages;
  List<PaymentAlternative> paymentAlternatives;
  String token;

  // Prerequisites
  private Map<Long, Seating> seatingMap;
  private Map<Long, Technology> technologyMap;
  private Map<Long, FoodBeverage> foodMap;
  private Map<Long, CitySimplified> cityMap;

  // DateFragment
  private List<AvailableRoom> searchResult;

  // SearchResultFragment
  private AvailableRoom selectedRoom; // Through AvailableRoomsRecyclerAdapter

  // ConfirmRoomFragment
  private ConferenceRoomSeating selectedRoomSeating;
  private long timeSlotId;

  //----- Constructors -----//
  public BookingCoordinator(Bundle apiData) {
    citiesWithVenues = apiData.getParcelableArrayList(Helper.BUNDLE_CITIES);
    createCityMap(citiesWithVenues);

    seatings = apiData.getParcelableArrayList(Helper.BUNDLE_STANDARD_SEATING);
    createSeatingMap(seatings);

    technologies = apiData.getParcelableArrayList(Helper.BUNDLE_TECHNOLOGIES);
    createTechnologyMap(technologies);

    foodBeverages = apiData.getParcelableArrayList(Helper.BUNDLE_FOOD_BEVARAGE_LIST);
    createFoodMap(foodBeverages);

    paymentAlternatives = apiData.getParcelableArrayList(Helper.BUNDLE_PAYMENT_ALTERNATIVES);
    token = apiData.getString(Helper.BUNDLE_TOKEN);
  }

  //----- Methods -----//
  private void log(String msg) {
    Log.i(Logging.BookingCoordinator, msg);
  }

  public void createSeatingMap(List<Seating> seatings) {
    seatingMap = seatings.stream()
        .collect(Collectors.toMap(
            Seating::getId, // Key
            seating -> seating // Value
        ));
  }

  public void createTechnologyMap(List<Technology> technologies) {
    technologyMap = technologies.stream()
        .collect(Collectors.toMap(
            Technology::getId, // Key
            technology -> technology // Value
        ));
  }

  public void createFoodMap(List<FoodBeverage> foodBeverages) {
    foodMap = foodBeverages.stream()
        .collect(Collectors.toMap(
            FoodBeverage::getId,
            foodBeverage -> foodBeverage
        ));
  }

  public void createCityMap(List<CitySimplified> cities) {
    cityMap = cities.stream()
        .collect(Collectors.toMap(
            CitySimplified::getId, // Key
            citySimplified -> citySimplified // Value
        ));
  }

  //----- Setters -----//
  public void setSearchResult(List<AvailableRoom> searchResult) {
    log(String.format("Adding search results, number of results: %s", searchResult.size()));
    this.searchResult = searchResult;
  }

  public void setSelectedRoom(AvailableRoom selectedRoom) {
    log(String.format("Selecting room: %s", selectedRoom));
    this.selectedRoom = selectedRoom;
  }

  public void setSelectedRoomSeating(ConferenceRoomSeating selectedRoomSeating) {
    log(String.format("Seating in selected room: %s", selectedRoomSeating));
    this.selectedRoomSeating = selectedRoomSeating;
  }

  public void setTimeSlotId(long timeSlotId) {
    log(String.format("Saving time slot id: %s", timeSlotId));
    this.timeSlotId = timeSlotId;
  }

  //----- Getters -----//
  public List<CitySimplified> getCitiesWithVenues() {
    return citiesWithVenues;
  }

  public List<Seating> getSeatings() {
    return seatings;
  }

  public List<Technology> getTechnologies() {
    return technologies;
  }

  public List<FoodBeverage> getFoodBeverages() {
    return foodBeverages;
  }

  public List<PaymentAlternative> getPaymentAlternatives() {
    return paymentAlternatives;
  }

  public String getToken() {
    return token;
  }

  public Map<Long, Seating> getSeatingMap() {
    return seatingMap;
  }

  public Map<Long, Technology> getTechnologyMap() {
    return technologyMap;
  }

  public Map<Long, FoodBeverage> getFoodMap() {
    return foodMap;
  }

  public Map<Long, CitySimplified> getCityMap() {
    return cityMap;
  }

  public List<AvailableRoom> getSearchResult() {
    return searchResult;
  }

  public AvailableRoom getSelectedRoom() {
    return selectedRoom;
  }

  public ConferenceRoomSeating getSelectedRoomSeating() {
    return selectedRoomSeating;
  }

  public long getTimeSlotId() {
    return timeSlotId;
  }
}
