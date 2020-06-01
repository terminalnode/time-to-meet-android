package com.example.timetomeet.retrofit.entity;

import com.google.gson.annotations.SerializedName;

public class BookingSelectableTechnologyAdd {
  @SerializedName("conferenceRoomAvailability")
  private Long conferenceRoomAvailability;

  @SerializedName("technology")
  private Long technology;

  //----- Constructors -----//
  public BookingSelectableTechnologyAdd() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BookingSelectableTechnologyAdd{");
    sb.append("conferenceRoomAvailability=").append(conferenceRoomAvailability);
    sb.append(", technology=").append(technology);
    sb.append('}');
    return sb.toString();
  }

  //----- Setters -----//
  public void setConferenceRoomAvailability(Long conferenceRoomAvailability) {
    this.conferenceRoomAvailability = conferenceRoomAvailability;
  }

  public void setTechnology(Long technology) {
    this.technology = technology;
  }

  //----- Getters -----//
  public Long getConferenceRoomAvailability() {
    return conferenceRoomAvailability;
  }

  public Long getTechnology() {
    return technology;
  }
}
