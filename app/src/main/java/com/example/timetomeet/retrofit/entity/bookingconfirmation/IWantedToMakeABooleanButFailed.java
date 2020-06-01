package com.example.timetomeet.retrofit.entity.bookingconfirmation;

import com.google.gson.annotations.SerializedName;

public class IWantedToMakeABooleanButFailed {
  @SerializedName("true")
  private String oneOrZero;

  //----- Constructors -----//
  public IWantedToMakeABooleanButFailed() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    return "" + isTrue();
  }

  public boolean isTrue() {
    return "1".equals(oneOrZero);
  }

  //----- Setters -----//
  public void setOneOrZero(String oneOrZero) {
    this.oneOrZero = oneOrZero;
  }

  //----- Getters -----//
  public String getOneOrZero() {
    return oneOrZero;
  }
}
