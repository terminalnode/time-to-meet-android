package com.example.timetomeet.retrofit.entity.bookingconfirmation;

import com.google.gson.annotations.SerializedName;

public class BookedConferenceRoomWithPrice {
  @SerializedName("price")
  private Double price;

  @SerializedName("conference_room_title")
  private String conferenceRoomTitle;

  @SerializedName("chosen_seating")
  private String chosenSeating;

  //----- Constructors -----//
  public BookedConferenceRoomWithPrice() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BookedConferenceRoomWithPrice{");
    sb.append("price=").append(price);
    sb.append(", conferenceRoomTitle='").append(conferenceRoomTitle).append('\'');
    sb.append(", chosenSeating='").append(chosenSeating).append('\'');
    sb.append('}');
    return sb.toString();
  }

  //----- Setters -----//
  public void setPrice(Double price) {
    this.price = price;
  }

  public void setConferenceRoomTitle(String conferenceRoomTitle) {
    this.conferenceRoomTitle = conferenceRoomTitle;
  }

  public void setChosenSeating(String chosenSeating) {
    this.chosenSeating = chosenSeating;
  }

  //----- Getters -----//
  public Double getPrice() {
    return price;
  }

  public String getConferenceRoomTitle() {
    return conferenceRoomTitle;
  }

  public String getChosenSeating() {
    return chosenSeating;
  }
}
