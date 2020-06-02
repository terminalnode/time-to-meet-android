package com.example.timetomeet.retrofit.entity.bookingconfirmation;

import com.google.gson.annotations.SerializedName;

public class IWantedToMakeAStringButFailed {
  @SerializedName("name")
  private String name;

  //----- Constructors -----//
  public IWantedToMakeAStringButFailed() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    return name;
  }

  //----- Setters -----//
  public void setName(String name) {
    this.name = name;
  }

  //----- Getters -----//
  public String getName() {
    return name;
  }
}
