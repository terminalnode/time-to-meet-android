package com.example.timetomeet.retrofit.entity;

import com.google.gson.annotations.SerializedName;

public class Seating {
  @SerializedName("id")
  private Long id;

  @SerializedName("name")
  private String nameSv;

  @SerializedName("nameEn")
  private String nameEn;

  @SerializedName("iconPath")
  private String iconPath;

  //----- Constructors -----//
  public Seating() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    return "Seating{" +
        "id=" + id +
        ", nameSv='" + nameSv + '\'' +
        ", nameEn='" + nameEn + '\'' +
        ", iconPath='" + iconPath + '\'' +
        '}';
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

  public void setIconPath(String iconPath) {
    this.iconPath = iconPath;
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

  public String getIconPath() {
    return iconPath;
  }
}
