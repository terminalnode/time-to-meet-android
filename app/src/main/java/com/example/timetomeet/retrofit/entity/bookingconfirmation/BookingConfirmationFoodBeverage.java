package com.example.timetomeet.retrofit.entity.bookingconfirmation;

import com.google.gson.annotations.SerializedName;

public class BookingConfirmationFoodBeverage {
  @SerializedName("foodBeverageName")
  private String name;

  @SerializedName("individual_price")
  private String individualPrice;

  @SerializedName("total_price")
  private String totalPrice;

  @SerializedName("str_amount")
  private String amount;

  //----- Constructors -----//
  public BookingConfirmationFoodBeverage() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BookingConfirmationFoodBeverage{");
    sb.append("name='").append(name).append('\'');
    sb.append(", individualPrice='").append(individualPrice).append('\'');
    sb.append(", totalPrice='").append(totalPrice).append('\'');
    sb.append(", amount='").append(amount).append('\'');
    sb.append('}');
    return sb.toString();
  }

  //----- Setters -----//
  public void setName(String name) {
    this.name = name;
  }

  public void setIndividualPrice(String individualPrice) {
    this.individualPrice = individualPrice;
  }

  public void setTotalPrice(String totalPrice) {
    this.totalPrice = totalPrice;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  //----- Getters -----//
  public String getName() {
    return name;
  }

  public String getIndividualPrice() {
    return individualPrice;
  }

  public String getTotalPrice() {
    return totalPrice;
  }

  public String getAmount() {
    return amount;
  }
}
