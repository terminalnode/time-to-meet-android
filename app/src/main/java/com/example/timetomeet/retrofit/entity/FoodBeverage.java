package com.example.timetomeet.retrofit.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.timetomeet.retrofit.LocalizableName;
import com.google.gson.annotations.SerializedName;

public class FoodBeverage implements Parcelable, LocalizableName {
  @SerializedName("id")
  private Long id;

  @SerializedName("name")
  private String nameSv;

  @SerializedName("name_en")
  private String nameEn;

  @SerializedName("foodBeverageGroup")
  private Long foodBeverageGroup;

  @SerializedName("state_id")
  private Long stateId;

  //----- Constructors -----//
  public FoodBeverage() {
  }

  public FoodBeverage(Long id, String nameSv, String nameEn, Long foodBeverageGroup, Long stateId) {
    this.id = id;
    this.nameSv = nameSv;
    this.nameEn = nameEn;
    this.foodBeverageGroup = foodBeverageGroup;
    this.stateId = stateId;
  }

  //----- Methods -----//
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("FoodBevarageList{");
    sb.append("id=").append(id);
    sb.append(", nameSv='").append(nameSv).append('\'');
    sb.append(", nameEn='").append(nameEn).append('\'');
    sb.append(", foodBeverageGroup=").append(foodBeverageGroup);
    sb.append(", stateId=").append(stateId);
    sb.append('}');
    return sb.toString();
  }

  @Override
  public int describeContents() { return 0; }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(this.id);
    dest.writeString(this.nameSv);
    dest.writeString(this.nameEn);
    dest.writeValue(this.foodBeverageGroup);
    dest.writeValue(this.stateId);
  }

  protected FoodBeverage(Parcel in) {
    this.id = (Long) in.readValue(Long.class.getClassLoader());
    this.nameSv = in.readString();
    this.nameEn = in.readString();
    this.foodBeverageGroup = (Long) in.readValue(Long.class.getClassLoader());
    this.stateId = (Long) in.readValue(Long.class.getClassLoader());
  }

  public static final Parcelable.Creator<FoodBeverage> CREATOR = new Parcelable.Creator<FoodBeverage>() {
    @Override
    public FoodBeverage createFromParcel(Parcel source) { return new FoodBeverage(source); }

    @Override
    public FoodBeverage[] newArray(int size) { return new FoodBeverage[size]; }
  };

  //----- Setters -----//
  public void setId(Long id) { this.id = id; }

  public void setNameSv(String nameSv) { this.nameSv = nameSv; }

  public void setNameEn(String nameEn) { this.nameEn = nameEn; }

  public void setFoodBeverageGroup(Long foodBeverageGroup) { this.foodBeverageGroup = foodBeverageGroup; }

  public void setStateId(Long stateId) { this.stateId = stateId; }

  //----- Getters -----//
  public Long getId() { return id; }

  public String getNameSv() { return nameSv; }

  public String getNameEn() { return nameEn; }

  public Long getFoodBeverageGroup() { return foodBeverageGroup; }

  public Long getStateId() { return stateId; }
}
