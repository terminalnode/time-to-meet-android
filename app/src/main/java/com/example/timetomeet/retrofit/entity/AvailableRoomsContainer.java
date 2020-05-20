package com.example.timetomeet.retrofit.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvailableRoomsContainer {
  @SerializedName("message")
  private String message;

  @SerializedName("result")
  private List<AvailableRoom> result;

  //----- Constructors -----//
  public AvailableRoomsContainer() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    return String.format("AvailableRoomsContainer{message='%s', result=%s}", message, result);
  }

  //----- Setters -----//
  public void setMessage(String message) {
    this.message = message;
  }

  public void setResult(List<AvailableRoom> result) {
    this.result = result;
  }

  //----- Getters -----//
  public String getMessage() {
    return message;
  }

  public List<AvailableRoom> getResult() {
    return result;
  }
}
