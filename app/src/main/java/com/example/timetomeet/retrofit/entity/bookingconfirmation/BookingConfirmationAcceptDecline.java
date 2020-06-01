package com.example.timetomeet.retrofit.entity.bookingconfirmation;

import com.google.gson.annotations.SerializedName;

public class BookingConfirmationAcceptDecline {
  @SerializedName("accept_url")
  private String acceptUrl;

  @SerializedName("decline_url")
  private String declineUrl;

  //----- Constructors -----//
  public BookingConfirmationAcceptDecline() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BookingConfirmationAcceptDecline{");
    sb.append("acceptUrl='").append(acceptUrl).append('\'');
    sb.append(", declineUrl='").append(declineUrl).append('\'');
    sb.append('}');
    return sb.toString();
  }

  //----- Setters -----//
  public void setAcceptUrl(String acceptUrl) {
    this.acceptUrl = acceptUrl;
  }

  public void setDeclineUrl(String declineUrl) {
    this.declineUrl = declineUrl;
  }

  //----- Getters -----//
  public String getAcceptUrl() {
    return acceptUrl;
  }

  public String getDeclineUrl() {
    return declineUrl;
  }
}
