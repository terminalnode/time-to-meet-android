package com.example.timetomeet.retrofit.entity;

import com.google.gson.annotations.SerializedName;

public class City {
  @SerializedName("id")
  private String id;

  @SerializedName("name")
  private String nameSv;

  @SerializedName("name_en")
  private String nameEn;

  @SerializedName("topLocation")
  private Boolean topLocation;

  @SerializedName("region")
  private String region;

  @SerializedName("county")
  private String county;

  @SerializedName("geoCoordinate")
  private String geoCoordinate;

  @SerializedName("title")
  private String title;

  @SerializedName("h1")
  private String h1;

  @SerializedName("seo_text")
  private String seoText;

  //----- Constructors -----//
  public City() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    return "City{" +
        "id='" + id + '\'' +
        ", nameSv='" + nameSv + '\'' +
        ", nameEn='" + nameEn + '\'' +
        ", topLocation=" + topLocation +
        ", region='" + region + '\'' +
        ", county='" + county + '\'' +
        ", geoCoordinate='" + geoCoordinate + '\'' +
        ", title='" + title + '\'' +
        ", h1='" + h1 + '\'' +
        ", seoText='" + seoText + '\'' +
        '}';
  }

  //----- Setters -----//
  public void setId(String id) {
    this.id = id;
  }

  public void setNameSv(String nameSv) {
    this.nameSv = nameSv;
  }

  public void setNameEn(String nameEn) {
    this.nameEn = nameEn;
  }

  public void setTopLocation(Boolean topLocation) {
    this.topLocation = topLocation;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public void setCounty(String county) {
    this.county = county;
  }

  public void setGeoCoordinate(String geoCoordinate) {
    this.geoCoordinate = geoCoordinate;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setH1(String h1) {
    this.h1 = h1;
  }

  public void setSeoText(String seoText) {
    this.seoText = seoText;
  }

  //----- Getters -----//
  public String getId() {
    return id;
  }

  public String getNameSv() {
    return nameSv;
  }

  public String getNameEn() {
    return nameEn;
  }

  public Boolean getTopLocation() {
    return topLocation;
  }

  public String getRegion() {
    return region;
  }

  public String getCounty() {
    return county;
  }

  public String getGeoCoordinate() {
    return geoCoordinate;
  }

  public String getTitle() {
    return title;
  }

  public String getH1() {
    return h1;
  }

  public String getSeoText() {
    return seoText;
  }
}
