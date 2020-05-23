package com.example.timetomeet.retrofit.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Seating implements Parcelable {
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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(this.id);
    dest.writeString(this.nameSv);
    dest.writeString(this.nameEn);
    dest.writeString(this.iconPath);
  }

  protected Seating(Parcel in) {
    this.id = (Long) in.readValue(Long.class.getClassLoader());
    this.nameSv = in.readString();
    this.nameEn = in.readString();
    this.iconPath = in.readString();
  }

  public static final Parcelable.Creator<Seating> CREATOR = new Parcelable.Creator<Seating>() {
    @Override
    public Seating createFromParcel(Parcel source) {
      return new Seating(source);
    }

    @Override
    public Seating[] newArray(int size) {
      return new Seating[size];
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
