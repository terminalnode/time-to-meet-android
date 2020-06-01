package com.example.timetomeet.retrofit.entity.bookingconfirmation;

import com.google.gson.annotations.SerializedName;

public class BookingConfirmationPostmarkBool {
  @SerializedName("wantInfo")
  private IWantedToMakeABooleanButFailed wantInfo;

  @SerializedName("wantHotelInfo")
  private IWantedToMakeABooleanButFailed wantHotelInfo;

  @SerializedName("wantActivityInfo")
  private IWantedToMakeABooleanButFailed wantActivityInfo;

  //@SerializedName("specialRequest")
  //private Object specialRequest; // TODO Create booking with special request and see what happens

  @SerializedName("notAgreementCustomer")
  private IWantedToMakeABooleanButFailed notAgreementCustomer;

  //----- Constructors -----//
  public BookingConfirmationPostmarkBool() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BookingConfirmationPostmarkBool{");
    sb.append("wantInfo=").append(wantInfo);
    sb.append(", wantHotelInfo=").append(wantHotelInfo);
    sb.append(", wantActivityInfo=").append(wantActivityInfo);
    sb.append(", notAgreementCustomer=").append(notAgreementCustomer);
    sb.append('}');
    return sb.toString();
  }

  //----- Setters -----//
  public void setWantInfo(IWantedToMakeABooleanButFailed wantInfo) {
    this.wantInfo = wantInfo;
  }

  public void setWantHotelInfo(IWantedToMakeABooleanButFailed wantHotelInfo) {
    this.wantHotelInfo = wantHotelInfo;
  }

  public void setWantActivityInfo(IWantedToMakeABooleanButFailed wantActivityInfo) {
    this.wantActivityInfo = wantActivityInfo;
  }

  public void setNotAgreementCustomer(IWantedToMakeABooleanButFailed notAgreementCustomer) {
    this.notAgreementCustomer = notAgreementCustomer;
  }

  //----- Getters -----//
  public IWantedToMakeABooleanButFailed getWantInfo() {
    return wantInfo;
  }

  public IWantedToMakeABooleanButFailed getWantHotelInfo() {
    return wantHotelInfo;
  }

  public IWantedToMakeABooleanButFailed getWantActivityInfo() {
    return wantActivityInfo;
  }

  public IWantedToMakeABooleanButFailed getNotAgreementCustomer() {
    return notAgreementCustomer;
  }
}
