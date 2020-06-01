package com.example.timetomeet.retrofit.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.timetomeet.retrofit.LocalizableName;
import com.google.gson.annotations.SerializedName;

public class FoodBeverageGroup implements Parcelable, LocalizableName {
  @SerializedName("id")
  private Long id;

  @SerializedName("name")
  private String nameSv;

  @SerializedName("name_en")
  private String nameEn;

  //----- Constructors -----//
  public FoodBeverageGroup() {
  }

  public FoodBeverageGroup(Long id, String nameSv, String nameEn) {
    this.id = id;
    this.nameSv = nameSv;
    this.nameEn = nameEn;
  }

  //----- Methods -----//
  @Override
  public String toString() {
    return String.format("FoodBevarageGroupList{id=%s, nameSv='%s', nameEn='%s'}", id, nameSv, nameEn);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(this.id);
    dest.writeString(this.nameSv);
    dest.writeString(this.nameEn);
  }

  protected FoodBeverageGroup(Parcel in) {
    this.id = (Long) in.readValue(Long.class.getClassLoader());
    this.nameSv = in.readString();
    this.nameEn = in.readString();
  }

  public static final Parcelable.Creator<FoodBeverageGroup> CREATOR = new Parcelable.Creator<FoodBeverageGroup>() {
    @Override
    public FoodBeverageGroup createFromParcel(Parcel source) {
      return new FoodBeverageGroup(source);
    }

    @Override
    public FoodBeverageGroup[] newArray(int size) {
      return new FoodBeverageGroup[size];
    }
  };

  //----- Setters -----//
  public void setId(Long id) {
    this.id = id;
  }

  public void setNameSv(String nameSv) {
    this.nameSv = nameSv;
  }

  public void setNameEn(String nameEn) {
    this.nameEn = nameEn;
  }

  //----- Getters -----//
  public Long getId() {
    return id;
  }

  public String getNameSv() {
    return nameSv;
  }

  public String getNameEn() {
    return nameEn;
  }

}
