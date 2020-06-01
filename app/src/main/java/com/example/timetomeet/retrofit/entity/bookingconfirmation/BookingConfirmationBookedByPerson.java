package com.example.timetomeet.retrofit.entity.bookingconfirmation;

import com.google.gson.annotations.SerializedName;

public class BookingConfirmationBookedByPerson {
  @SerializedName("books_for_organization")
  private String organizationName;

  @SerializedName("books_for_organizationNumber")
  private String organizationNumber;

  @SerializedName("email")
  private String email;

  //----- Constructors -----//
  public BookingConfirmationBookedByPerson() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BookingConfirmationBookedByPerson{");
    sb.append("organizationName='").append(organizationName).append('\'');
    sb.append(", organizationNumber='").append(organizationNumber).append('\'');
    sb.append(", email='").append(email).append('\'');
    sb.append('}');
    return sb.toString();
  }

  //----- Setters -----//
  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
  }

  public void setOrganizationNumber(String organizationNumber) {
    this.organizationNumber = organizationNumber;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  //----- Getters -----//
  public String getOrganizationName() {
    return organizationName;
  }

  public String getOrganizationNumber() {
    return organizationNumber;
  }

  public String getEmail() {
    return email;
  }
}
