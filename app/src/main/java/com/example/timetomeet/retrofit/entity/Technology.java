package com.example.timetomeet.retrofit.entity;

import com.google.gson.annotations.SerializedName;

public class Technology {
  @SerializedName("id")
  private Long id;

  @SerializedName("name")
  private String nameSv;

  @SerializedName("name_en")
  private String nameEn;

  @SerializedName("technologyGroup")
  private Long technologyGroup;

  @SerializedName("state_id")
  private Long stateId;

  //----- Constructors -----//
  public Technology() {
  }

  public Technology(Long id, String nameSv, String nameEn, Long technologyGroup, Long stateId) {
    this.id = id;
    this.nameSv = nameSv;
    this.nameEn = nameEn;
    this.technologyGroup = technologyGroup;
    this.stateId = stateId;
  }

  //----- Methods -----//
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Technology{");
    sb.append("id='").append(id).append('\'');
    sb.append(", name='").append(nameSv).append('\'');
    sb.append(", nameEn='").append(nameEn).append('\'');
    sb.append(", technologyGroup='").append(technologyGroup).append('\'');
    sb.append(", stateId='").append(stateId).append('\'');
    sb.append('}');
    return sb.toString();
  }

  public String getLocalizedName(String locale) {
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

  public void setTechnologyGroup(Long technologyGroup) {
    this.technologyGroup = technologyGroup;
  }

  public void setStateId(Long stateId) {
    this.stateId = stateId;
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

  public Long getTechnologyGroup() {
    return technologyGroup;
  }

  public Long getStateId() {
    return stateId;
  }
}
