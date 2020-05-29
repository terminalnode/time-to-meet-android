package com.example.timetomeet.retrofit.entity;

import com.google.gson.annotations.SerializedName;

public class TimeSlotAdd {
  @SerializedName("id")
  Long id;

  @SerializedName("chosenSeating")
  Long chosenSeating;

  //----- Constructors -----//
  public TimeSlotAdd() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("TimeSlotAdd{");
    sb.append("id=").append(id);
    sb.append(", seatingId=").append(chosenSeating);
    sb.append('}');
    return sb.toString();
  }

  //----- Setters -----//
  public void setId(Long id) {
    this.id = id;
  }

  public void setChosenSeating(Long chosenSeating) {
    this.chosenSeating = chosenSeating;
  }

  //----- Getters -----//
  public Long getId() {
    return id;
  }

  public Long getChosenSeating() {
    return chosenSeating;
  }
}
