package com.example.timetomeet.retrofit.entity.bookingconfirmation;

import com.google.gson.annotations.SerializedName;

public class BookingConfirmationVenue {
  @SerializedName("online_url")
  private String onlineUrl;

  @SerializedName("confirmation_email")
  private String confirmationEmail;

  @SerializedName("name")
  private String name;

  @SerializedName("visiting_address")
  private String visitingAddress;

  @SerializedName("seo_url")
  private String seoUrl;

  //----- Constructors -----//
  public BookingConfirmationVenue() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BookingConfirmationVenue{");
    sb.append("onlineUrl='").append(onlineUrl).append('\'');
    sb.append(", confirmationEmail='").append(confirmationEmail).append('\'');
    sb.append(", name='").append(name).append('\'');
    sb.append(", visitingAddress='").append(visitingAddress).append('\'');
    sb.append(", seoUrl='").append(seoUrl).append('\'');
    sb.append('}');
    return sb.toString();
  }

  //----- Setters -----//
  public void setOnlineUrl(String onlineUrl) {
    this.onlineUrl = onlineUrl;
  }

  public void setConfirmationEmail(String confirmationEmail) {
    this.confirmationEmail = confirmationEmail;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setVisitingAddress(String visitingAddress) {
    this.visitingAddress = visitingAddress;
  }

  public void setSeoUrl(String seoUrl) {
    this.seoUrl = seoUrl;
  }

  //----- Getters -----//
  public String getOnlineUrl() {
    return onlineUrl;
  }

  public String getConfirmationEmail() {
    return confirmationEmail;
  }

  public String getName() {
    return name;
  }

  public String getVisitingAddress() {
    return visitingAddress;
  }

  public String getSeoUrl() {
    return seoUrl;
  }
}
