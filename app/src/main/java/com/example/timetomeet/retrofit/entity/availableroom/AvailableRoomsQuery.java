package com.example.timetomeet.retrofit.entity.availableroom;

import com.google.gson.annotations.SerializedName;

public class AvailableRoomsQuery {
  @SerializedName("objectIds")
  private String objectIds;

  @SerializedName("objectType")
  private String objectType;

  @SerializedName("fromDate")
  private String fromDate;

  @SerializedName("toDate")
  private String toDate;

  //----- Constructors -----//
  public AvailableRoomsQuery() {
  }

  public AvailableRoomsQuery(String objectIds, String fromDate, String toDate) {
    this.objectIds = objectIds;
    this.fromDate = fromDate;
    this.toDate = toDate;
  }

  //----- Methods -----//
  @Override
  public String toString() {
    return "AvailableRoomsQuery{" +
        "objectIds='" + objectIds + '\'' +
        ", objectType='" + objectType + '\'' +
        ", fromDate='" + fromDate + '\'' +
        ", toDate='" + toDate + '\'' +
        '}';
  }

  public void setTypeCity() {
    objectType = "city";
  }

  public void setTypePlant() {
    objectType = "plant";
  }

  public void setTypeOrganization() {
    objectType = "organization";
  }

  public void setTypeRoom() {
    objectType = "room";
  }

  //----- Setters -----//
  public void setObjectIds(String objectIds) {
    this.objectIds = objectIds;
  }

  public void setObjectType(String objectType) {
    this.objectType = objectType;
  }

  public void setFromDate(String fromDate) {
    this.fromDate = fromDate;
  }

  public void setToDate(String toDate) {
    this.toDate = toDate;
  }

  //----- Getters -----//
  public String getObjectIds() {
    return objectIds;
  }

  public String getObjectType() {
    return objectType;
  }

  public String getFromDate() {
    return fromDate;
  }

  public String getToDate() {
    return toDate;
  }
}
