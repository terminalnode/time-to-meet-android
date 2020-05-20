package com.example.timetomeet.retrofit.entity;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class CitySimplified {
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
  @Override
  public String toString() {
    return String.format("CitySimplified{id=%s, nameSv='%s', nameEn='%s'}", id, nameSv, nameEn);
  }

  public String getLocalizedName(String locale) {
    Log.i("YOLO", locale);
    switch (locale) {
      case "en": return nameEn;
      case "sv": return nameSv;
      default: return nameSv;
    }
  }

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
