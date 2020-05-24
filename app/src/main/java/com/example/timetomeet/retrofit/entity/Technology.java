package com.example.timetomeet.retrofit.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.timetomeet.retrofit.LocalizableName;
import com.google.gson.annotations.SerializedName;

public class Technology implements Parcelable, LocalizableName {
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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(this.id);
    dest.writeString(this.nameSv);
    dest.writeString(this.nameEn);
    dest.writeValue(this.technologyGroup);
    dest.writeValue(this.stateId);
  }

  protected Technology(Parcel in) {
    this.id = (Long) in.readValue(Long.class.getClassLoader());
    this.nameSv = in.readString();
    this.nameEn = in.readString();
    this.technologyGroup = (Long) in.readValue(Long.class.getClassLoader());
    this.stateId = (Long) in.readValue(Long.class.getClassLoader());
  }

  public static final Parcelable.Creator<Technology> CREATOR = new Parcelable.Creator<Technology>() {
    @Override
    public Technology createFromParcel(Parcel source) {
      return new Technology(source);
    }

    @Override
    public Technology[] newArray(int size) {
      return new Technology[size];
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
