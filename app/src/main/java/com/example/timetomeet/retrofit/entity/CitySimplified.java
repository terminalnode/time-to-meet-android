package com.example.timetomeet.retrofit.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CitySimplified implements Parcelable {
  @SerializedName("id")
  private Long id;

  @SerializedName("name")
  private String nameSv;

  @SerializedName("name_en")
  private String nameEn;

  //----- Constructors -----//
  public CitySimplified() {
  }

  public CitySimplified(Long id, String nameSv, String nameEn) {
    this.id = id;
    this.nameSv = nameSv;
    this.nameEn = nameEn;
  }

  //----- Methods -----//
  public String getLocalizedName(String locale) {
    switch (locale) {
      case "en": return nameEn;
      case "sv": return nameSv;
      default: return nameSv;
    }
  }

  @Override
  public String toString() {
    return String.format("CitySimplified{id=%s, nameSv='%s', nameEn='%s'}", id, nameSv, nameEn);
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

  protected CitySimplified(Parcel in) {
    this.id = (Long) in.readValue(Long.class.getClassLoader());
    this.nameSv = in.readString();
    this.nameEn = in.readString();
  }

  public static final Parcelable.Creator<CitySimplified> CREATOR = new Parcelable.Creator<CitySimplified>() {
    @Override
    public CitySimplified createFromParcel(Parcel source) {
      return new CitySimplified(source);
    }

    @Override
    public CitySimplified[] newArray(int size) {
      return new CitySimplified[size];
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
