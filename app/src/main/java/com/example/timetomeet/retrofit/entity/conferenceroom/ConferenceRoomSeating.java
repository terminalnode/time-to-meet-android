package com.example.timetomeet.retrofit.entity.conferenceroom;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ConferenceRoomSeating implements Parcelable {
  @SerializedName("id")
  private Long id;

  @SerializedName("conferenceRoom")
  private Long conferenceRoomId;

  @SerializedName("standardSeating")
  private Long seatingId;

  @SerializedName("numberOfSeat")
  private Integer numberOfSeats;

  //----- Constructors -----//
  public ConferenceRoomSeating() {
  }

  //----- Methods -----//
  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(this.id);
    dest.writeValue(this.conferenceRoomId);
    dest.writeValue(this.seatingId);
    dest.writeValue(this.numberOfSeats);
  }

  protected ConferenceRoomSeating(Parcel in) {
    this.id = (Long) in.readValue(Long.class.getClassLoader());
    this.conferenceRoomId = (Long) in.readValue(Long.class.getClassLoader());
    this.seatingId = (Long) in.readValue(Long.class.getClassLoader());
    this.numberOfSeats = (Integer) in.readValue(Integer.class.getClassLoader());
  }

  public static final Creator<ConferenceRoomSeating> CREATOR = new Creator<ConferenceRoomSeating>() {
    @Override
    public ConferenceRoomSeating createFromParcel(Parcel source) {
      return new ConferenceRoomSeating(source);
    }

    @Override
    public ConferenceRoomSeating[] newArray(int size) {
      return new ConferenceRoomSeating[size];
    }
  };

  @Override
  public String toString() {
    return "ConferenceRoomSeating{" +
        "id=" + id +
        ", conferenceRoomId=" + conferenceRoomId +
        ", seatingId=" + seatingId +
        ", numberOfSeats=" + numberOfSeats +
        '}';
  }

  //----- Setters -----//
  public void setId(Long id) {
    this.id = id;
  }

  public void setConferenceRoomId(Long conferenceRoomId) {
    this.conferenceRoomId = conferenceRoomId;
  }

  public void setSeatingId(Long seatingId) {
    this.seatingId = seatingId;
  }

  public void setNumberOfSeats(Integer numberOfSeats) {
    this.numberOfSeats = numberOfSeats;
  }

  //----- Getters -----//
  public Long getId() {
    return id;
  }

  public Long getConferenceRoomId() {
    return conferenceRoomId;
  }

  public Long getSeatingId() {
    return seatingId;
  }

  public Integer getNumberOfSeats() {
    return numberOfSeats;
  }
}
