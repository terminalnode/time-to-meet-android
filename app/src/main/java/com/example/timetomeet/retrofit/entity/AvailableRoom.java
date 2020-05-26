package com.example.timetomeet.retrofit.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AvailableRoom implements Parcelable {
  @SerializedName("fullDayPrice")
  private Double fullDayPrice;

  @SerializedName("preNoonPrice")
  private Double preNoonPrice;

  @SerializedName("afterNoonPrice")
  private Double afterNoonPrice;

  @SerializedName("start")
  private String startDate;

  @SerializedName("id31")
  private Long id31;

  @SerializedName("id32")
  private Long id32;

  @SerializedName("room_id")
  private Long roomId;

  @SerializedName("plant_id")
  private Long plantId;

  @SerializedName("org_id")
  private Long organizationId;

  @SerializedName("city_id")
  private Long cityId;

  private CitySimplified associatedCity;
  private Venue associatedVenue;

  //----- Constructors -----//
  public AvailableRoom() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    return "AvailableRoom{" +
        "fullDayPrice=" + fullDayPrice +
        ", preNoonPrice=" + preNoonPrice +
        ", afterNoonPrice=" + afterNoonPrice +
        ", startDate='" + startDate + '\'' +
        ", id31=" + id31 +
        ", id32=" + id32 +
        ", roomId=" + roomId +
        ", plantId=" + plantId +
        ", organizationId=" + organizationId +
        ", cityId=" + cityId +
        '}';
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(this.fullDayPrice);
    dest.writeValue(this.preNoonPrice);
    dest.writeValue(this.afterNoonPrice);
    dest.writeString(this.startDate);
    dest.writeValue(this.id31);
    dest.writeValue(this.id32);
    dest.writeValue(this.roomId);
    dest.writeValue(this.plantId);
    dest.writeValue(this.organizationId);
    dest.writeValue(this.cityId);
  }

  protected AvailableRoom(Parcel in) {
    this.fullDayPrice = (Double) in.readValue(Double.class.getClassLoader());
    this.preNoonPrice = (Double) in.readValue(Double.class.getClassLoader());
    this.afterNoonPrice = (Double) in.readValue(Double.class.getClassLoader());
    this.startDate = in.readString();
    this.id31 = (Long) in.readValue(Long.class.getClassLoader());
    this.id32 = (Long) in.readValue(Long.class.getClassLoader());
    this.roomId = (Long) in.readValue(Long.class.getClassLoader());
    this.plantId = (Long) in.readValue(Long.class.getClassLoader());
    this.organizationId = (Long) in.readValue(Long.class.getClassLoader());
    this.cityId = (Long) in.readValue(Long.class.getClassLoader());
  }

  public static final Parcelable.Creator<AvailableRoom> CREATOR = new Parcelable.Creator<AvailableRoom>() {
    @Override
    public AvailableRoom createFromParcel(Parcel source) {
      return new AvailableRoom(source);
    }

    @Override
    public AvailableRoom[] newArray(int size) {
      return new AvailableRoom[size];
    }
  };

  //----- Setters -----//
  public void setFullDayPrice(Double fullDayPrice) {
    this.fullDayPrice = fullDayPrice;
  }

  public void setPreNoonPrice(Double preNoonPrice) {
    this.preNoonPrice = preNoonPrice;
  }

  public void setAfterNoonPrice(Double afterNoonPrice) {
    this.afterNoonPrice = afterNoonPrice;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public void setId31(Long id31) {
    this.id31 = id31;
  }

  public void setId32(Long id32) {
    this.id32 = id32;
  }

  public void setRoomId(Long roomId) {
    this.roomId = roomId;
  }

  public void setPlantId(Long plantId) {
    this.plantId = plantId;
  }

  public void setOrganizationId(Long organizationId) {
    this.organizationId = organizationId;
  }

  public void setCityId(Long cityId) {
    this.cityId = cityId;
  }

  public void setAssociatedCity(CitySimplified associatedCity) {
    this.associatedCity = associatedCity;
  }

  public void setAssociatedVenue(Venue associatedVenue) {
    this.associatedVenue = associatedVenue;
  }

  //----- Getters -----//
  public Double getFullDayPrice() {
    return fullDayPrice;
  }

  public Double getPreNoonPrice() {
    return preNoonPrice;
  }

  public Double getAfterNoonPrice() {
    return afterNoonPrice;
  }

  public String getStartDate() {
    return startDate;
  }

  public Long getId31() {
    return id31;
  }

  public Long getId32() {
    return id32;
  }

  public Long getRoomId() {
    return roomId;
  }

  public Long getPlantId() {
    return plantId;
  }

  public Long getOrganizationId() {
    return organizationId;
  }

  public Long getCityId() {
    return cityId;
  }

  public CitySimplified getAssociatedCity() {
    return associatedCity;
  }

  public Venue getAssociatedVenue() {
    return associatedVenue;
  }
}
