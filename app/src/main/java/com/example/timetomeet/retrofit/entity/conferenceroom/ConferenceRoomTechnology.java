package com.example.timetomeet.retrofit.entity.conferenceroom;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ConferenceRoomTechnology implements Parcelable {
  @SerializedName("id")
  private Long id;

  @SerializedName("conferenceRoom")
  private Long roomId;

  @SerializedName("technology")
  private Long technology;

  @SerializedName("technologyAvailability")
  private Long technologyAvailability;

  private boolean selected;

  //----- Constructors -----//
  public ConferenceRoomTechnology() {
  }

  public ConferenceRoomTechnology(Long id, Long roomId, Long technology, Long technologyAvailability) {
    this.id = id;
    this.roomId = roomId;
    this.technology = technology;
    this.technologyAvailability = technologyAvailability;
  }

  //----- Methods -----//
  public boolean isIncluded() {
    return technologyAvailability == 1;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ConferenceRoomTechnology{");
    sb.append("id=").append(id);
    sb.append(", roomId=").append(roomId);
    sb.append(", technology=").append(technology);
    sb.append(", technologyAvailability=").append(technologyAvailability);
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
    dest.writeValue(this.roomId);
    dest.writeValue(this.technology);
    dest.writeValue(this.technologyAvailability);
  }

  protected ConferenceRoomTechnology(Parcel in) {
    this.id = (Long) in.readValue(Long.class.getClassLoader());
    this.roomId = (Long) in.readValue(Long.class.getClassLoader());
    this.technology = (Long) in.readValue(Long.class.getClassLoader());
    this.technologyAvailability = (Long) in.readValue(Long.class.getClassLoader());
  }

  public static final Parcelable.Creator<ConferenceRoomTechnology> CREATOR = new Parcelable.Creator<ConferenceRoomTechnology>() {
    @Override
    public ConferenceRoomTechnology createFromParcel(Parcel source) {
      return new ConferenceRoomTechnology(source);
    }

    @Override
    public ConferenceRoomTechnology[] newArray(int size) {
      return new ConferenceRoomTechnology[size];
    }
  };

  //----- Setters -----//
  public void setId(Long id) {
    this.id = id;
  }

  public void setRoomId(Long roomId) {
    this.roomId = roomId;
  }

  public void setTechnology(Long technology) {
    this.technology = technology;
  }

  public void setTechnologyAvailability(Long technologyAvailability) {
    this.technologyAvailability = technologyAvailability;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  //----- Getters -----//
  public Long getId() {
    return id;
  }

  public Long getRoomId() {
    return roomId;
  }

  public Long getTechnology() {
    return technology;
  }

  public Long getTechnologyAvailability() {
    return technologyAvailability;
  }

  public boolean isSelected() {
    return selected;
  }
}
