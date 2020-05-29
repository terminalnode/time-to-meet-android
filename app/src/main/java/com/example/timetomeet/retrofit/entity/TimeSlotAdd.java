package com.example.timetomeet.retrofit.entity;

import com.google.gson.annotations.SerializedName;

public class TimeSlotAdd {
  @SerializedName("id")
  Long id;

  @SerializedName("chosenSeating")
  Long seatingId;

  //----- Constructors -----//
  public TimeSlotAdd() {
  }

  //----- Setters -----//
  public void setId(Long id) {
    this.id = id;
  }

  public void setSeatingId(Long seatingId) {
    this.seatingId = seatingId;
  }

  //----- Getters -----//
  public Long getId() {
    return id;
  }

  public Long getSeatingId() {
    return seatingId;
  }
}
