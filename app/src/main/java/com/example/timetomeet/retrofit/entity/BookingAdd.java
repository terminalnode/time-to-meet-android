package com.example.timetomeet.retrofit.entity;

import com.google.gson.annotations.SerializedName;

public class BookingAdd {
  @SerializedName("paymentAlternative")
  private Long paymentAlternative;

  @SerializedName("wantHotelRoomInfo")
  private Boolean wantHotelRoomInfo;

  @SerializedName("wantActivityInfo")
  private Boolean wantActivityInfo;

  @SerializedName("specialRequest")
  private String specialRequest;

  @SerializedName("numberOfParticipants")
  private Integer numberOfParticipants;

  // "help_text": "1 = TimeToMeet website. 2 = Widget.
  // Default is 1 when no value is supplied or an invalid value is supplied."
  @SerializedName("bookingSourceSystem")
  private Integer bookingSourceSystem;

  // "help_text": "Only 0-9 and A-Z, or completely empty."
  @SerializedName("agreementNumber")
  private String agreementNumber;

  //----- Constructor -----//
  public BookingAdd() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BookingAdd{");
    sb.append("paymentAlternative=").append(paymentAlternative);
    sb.append(", wantHotelRoomInfo=").append(wantHotelRoomInfo);
    sb.append(", wantActivityInfo=").append(wantActivityInfo);
    sb.append(", specialRequest='").append(specialRequest).append('\'');
    sb.append(", numberOfParticipants=").append(numberOfParticipants);
    sb.append(", bookingSourceSystem=").append(bookingSourceSystem);
    sb.append(", agreementNumber='").append(agreementNumber).append('\'');
    sb.append('}');
    return sb.toString();
  }

  //----- Setters -----//
  public void setPaymentAlternative(Long paymentAlternative) {
    this.paymentAlternative = paymentAlternative;
  }

  public void setWantHotelRoomInfo(Boolean wantHotelRoomInfo) {
    this.wantHotelRoomInfo = wantHotelRoomInfo;
  }

  public void setWantActivityInfo(Boolean wantActivityInfo) {
    this.wantActivityInfo = wantActivityInfo;
  }

  public void setSpecialRequest(String specialRequest) {
    this.specialRequest = specialRequest;
  }

  public void setNumberOfParticipants(Integer numberOfParticipants) {
    this.numberOfParticipants = numberOfParticipants;
  }

  public void setBookingSourceSystem(Integer bookingSourceSystem) {
    this.bookingSourceSystem = bookingSourceSystem;
  }

  public void setAgreementNumber(String agreementNumber) {
    this.agreementNumber = agreementNumber;
  }

  //----- Getters -----//
  public Long getPaymentAlternative() {
    return paymentAlternative;
  }

  public Boolean getWantHotelRoomInfo() {
    return wantHotelRoomInfo;
  }

  public Boolean getWantActivityInfo() {
    return wantActivityInfo;
  }

  public String getSpecialRequest() {
    return specialRequest;
  }

  public Integer getNumberOfParticipants() {
    return numberOfParticipants;
  }

  public Integer getBookingSourceSystem() {
    return bookingSourceSystem;
  }

  public String getAgreementNumber() {
    return agreementNumber;
  }
}
