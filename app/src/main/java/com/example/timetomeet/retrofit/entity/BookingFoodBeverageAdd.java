package com.example.timetomeet.retrofit.entity;

import com.google.gson.annotations.SerializedName;

public class BookingFoodBeverageAdd {
  @SerializedName("conferenceRoomAvailability")
  private Long conferenceRoomAvailabilityId;

  @SerializedName("foodBeverage")
  private Long foodBeverageId;

  @SerializedName("amount")
  private Integer amount;

  @SerializedName("comment")
  private String comment;

  @SerializedName("timeToServe")
  private String timeToServe;

  //----- Constructors -----//
  public BookingFoodBeverageAdd() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BookingFoodBeverageAdd{");
    sb.append("conferenceRoomAvailabilityId=").append(conferenceRoomAvailabilityId);
    sb.append(", foodBeverageId=").append(foodBeverageId);
    sb.append(", amount=").append(amount);
    sb.append(", comment='").append(comment).append('\'');
    sb.append(", timeToServe='").append(timeToServe).append('\'');
    sb.append('}');
    return sb.toString();
  }

  //----- Setters -----//
  public void setConferenceRoomAvailabilityId(Long conferenceRoomAvailabilityId) {
    this.conferenceRoomAvailabilityId = conferenceRoomAvailabilityId;
  }

  public void setFoodBeverageId(Long foodBeverageId) {
    this.foodBeverageId = foodBeverageId;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public void setTimeToServe(String timeToServe) {
    this.timeToServe = timeToServe;
  }

  //----- Getters -----//
  public Long getConferenceRoomAvailabilityId() {
    return conferenceRoomAvailabilityId;
  }

  public Long getFoodBeverageId() {
    return foodBeverageId;
  }

  public Integer getAmount() {
    return amount;
  }

  public String getComment() {
    return comment;
  }

  public String getTimeToServe() {
    return timeToServe;
  }
}
