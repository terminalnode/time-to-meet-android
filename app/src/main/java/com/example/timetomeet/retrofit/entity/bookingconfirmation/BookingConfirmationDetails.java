package com.example.timetomeet.retrofit.entity.bookingconfirmation;

import com.google.gson.annotations.SerializedName;

public class BookingConfirmationDetails {
  @SerializedName("bookingNumber")
  private String bookingNumber;

  @SerializedName("emailStateText")
  private String emailStateText;

  @SerializedName("arrivalDate")
  private String arrivalDate;

  @SerializedName("arrivalTime")
  private String arrivalTime;

  @SerializedName("departTime")
  private String departTime;

  @SerializedName("blockDescription")
  private String blockDescription;

  //----- Constructors -----//
  public BookingConfirmationDetails() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BookingConfirmationDetails{");
    sb.append("bookingNumber='").append(bookingNumber).append('\'');
    sb.append(", emailStateText='").append(emailStateText).append('\'');
    sb.append(", arrivalDate='").append(arrivalDate).append('\'');
    sb.append(", arrivalTime='").append(arrivalTime).append('\'');
    sb.append(", departTime='").append(departTime).append('\'');
    sb.append(", blockDescription='").append(blockDescription).append('\'');
    sb.append('}');
    return sb.toString();
  }

  //----- Setters -----//
  public void setBookingNumber(String bookingNumber) {
    this.bookingNumber = bookingNumber;
  }

  public void setEmailStateText(String emailStateText) {
    this.emailStateText = emailStateText;
  }

  public void setArrivalDate(String arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  public void setArrivalTime(String arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public void setDepartTime(String departTime) {
    this.departTime = departTime;
  }

  public void setBlockDescription(String blockDescription) {
    this.blockDescription = blockDescription;
  }

  //----- Getters -----//
  public String getBookingNumber() {
    return bookingNumber;
  }

  public String getEmailStateText() {
    return emailStateText;
  }

  public String getArrivalDate() {
    return arrivalDate;
  }

  public String getArrivalTime() {
    return arrivalTime;
  }

  public String getDepartTime() {
    return departTime;
  }

  public String getBlockDescription() {
    return blockDescription;
  }
}
