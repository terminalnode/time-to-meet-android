package com.example.timetomeet.retrofit.entity;

import com.google.gson.annotations.SerializedName;

public class ConferenceRoomSeating {
  @SerializedName("id")
  private Long id;

  @SerializedName("conferenceRoom")
  private Long conferenceRoomId;

  @SerializedName("standardSeating")
  private Long seatingId;

  @SerializedName("numberOfSeat")
  private Integer numberOfSeats;

  //----- Constructors -----//
  public ConferenceRoomSeating() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    return "ConferenceRoomSeating{" +
        "id=" + id +
        ", conferenceRoomId=" + conferenceRoomId +
        ", seatingId=" + seatingId +
        ", numberOfSeats=" + numberOfSeats +
        '}';
  }

  //----- Setters -----//
  public void setId(Long id) {
    this.id = id;
  }

  public void setConferenceRoomId(Long conferenceRoomId) {
    this.conferenceRoomId = conferenceRoomId;
  }

  public void setSeatingId(Long seatingId) {
    this.seatingId = seatingId;
  }

  public void setNumberOfSeats(Integer numberOfSeats) {
    this.numberOfSeats = numberOfSeats;
  }

  //----- Getters -----//
  public Long getId() {
    return id;
  }

  public Long getConferenceRoomId() {
    return conferenceRoomId;
  }

  public Long getSeatingId() {
    return seatingId;
  }

  public Integer getNumberOfSeats() {
    return numberOfSeats;
  }
}
