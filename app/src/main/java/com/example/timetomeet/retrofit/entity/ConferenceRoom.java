package com.example.timetomeet.retrofit.entity;

import com.example.timetomeet.retrofit.LocalizableDescription;
import com.example.timetomeet.retrofit.LocalizableName;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConferenceRoom implements LocalizableName, LocalizableDescription {
  @SerializedName("id")
  private Long id;

  @SerializedName("name")
  private String nameSv;

  @SerializedName("name_en")
  private String nameEn;

  @SerializedName("description")
  private String descriptionSv;

  @SerializedName("description_en")
  private String descriptionEn;

  @SerializedName("plant")
  private Long plantId;

  @SerializedName("fullDayPrice")
  private Double fullDayPrice;

  @SerializedName("preNoonPrice")
  private Double preNoonPrice;

  @SerializedName("afterNoonPrice")
  private Double afterNoonPrice;

  @SerializedName("showSizeInsteadOfName")
  private Boolean showSizeInsteadOfName;

  @SerializedName("preNoonAvailabilityHourStart")
  private String beforeNoonHourStart;

  @SerializedName("preNoonAvailabilityHourEnd")
  private String beforeNoonHourEnd;

  @SerializedName("afterNoonAvailabilityHourStart")
  private String afterNoonHourStart;

  @SerializedName("afterNoonAvailabilityHourEnd")
  private String afterNoonHourEnd;

  @SerializedName("defaultSeating")
  private List<ConferenceRoomSeating> defaultSeating;

  @SerializedName("conferenceRoomBlob")
  private List<Long> conferenceRoomBlob;

  @SerializedName("state")
  private Long state;

  @SerializedName("technologies")
  private List<Technology> technologies;

  @SerializedName("seats")
  private List<Seating> seats;

  @SerializedName("blobs")
  private List<ConferenceRoomBlob> blobs;

  private List<ConferenceRoomTechnology> associatedConferenceRoomTechnologies;
  private List<FoodBeverage> associatedFoodBeverage;

  //----- Constructors -----//
  public ConferenceRoom() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    return "ConferenceRoom{" +
        "id=" + id +
        ", nameSv='" + nameSv + '\'' +
        ", nameEn='" + nameEn + '\'' +
        ", descriptionSv='" + descriptionSv + '\'' +
        ", descriptionEn='" + descriptionEn + '\'' +
        ", plantId=" + plantId +
        ", fullDayPrice=" + fullDayPrice +
        ", preNoonPrice=" + preNoonPrice +
        ", afterNoonPrice=" + afterNoonPrice +
        ", showSizeInsteadOfName=" + showSizeInsteadOfName +
        ", beforeNoonHourStart='" + beforeNoonHourStart + '\'' +
        ", beforeNoonHourEnd='" + beforeNoonHourEnd + '\'' +
        ", afterNoonHourStart='" + afterNoonHourStart + '\'' +
        ", afterNoonHourEnd='" + afterNoonHourEnd + '\'' +
        ", defaultSeating=" + defaultSeating +
        ", conferenceRoomBlob=" + conferenceRoomBlob +
        ", state=" + state +
        ", technologies=" + technologies +
        ", seats=" + seats +
        ", blobs=" + blobs +
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

  public void setDescriptionSv(String descriptionSv) {
    this.descriptionSv = descriptionSv;
  }

  public void setDescriptionEn(String descriptionEn) {
    this.descriptionEn = descriptionEn;
  }

  public void setPlantId(Long plantId) {
    this.plantId = plantId;
  }

  public void setFullDayPrice(Double fullDayPrice) {
    this.fullDayPrice = fullDayPrice;
  }

  public void setPreNoonPrice(Double preNoonPrice) {
    this.preNoonPrice = preNoonPrice;
  }

  public void setAfterNoonPrice(Double afterNoonPrice) {
    this.afterNoonPrice = afterNoonPrice;
  }

  public void setShowSizeInsteadOfName(Boolean showSizeInsteadOfName) {
    this.showSizeInsteadOfName = showSizeInsteadOfName;
  }

  public void setBeforeNoonHourStart(String beforeNoonHourStart) {
    this.beforeNoonHourStart = beforeNoonHourStart;
  }

  public void setBeforeNoonHourEnd(String beforeNoonHourEnd) {
    this.beforeNoonHourEnd = beforeNoonHourEnd;
  }

  public void setAfterNoonHourStart(String afterNoonHourStart) {
    this.afterNoonHourStart = afterNoonHourStart;
  }

  public void setAfterNoonHourEnd(String afterNoonHourEnd) {
    this.afterNoonHourEnd = afterNoonHourEnd;
  }

  public void setDefaultSeating(List<ConferenceRoomSeating> defaultSeating) {
    this.defaultSeating = defaultSeating;
  }

  public void setConferenceRoomBlob(List<Long> conferenceRoomBlob) {
    this.conferenceRoomBlob = conferenceRoomBlob;
  }

  public void setState(Long state) {
    this.state = state;
  }

  public void setTechnologies(List<Technology> technologies) {
    this.technologies = technologies;
  }

  public void setSeats(List<Seating> seats) {
    this.seats = seats;
  }

  public void setBlobs(List<ConferenceRoomBlob> blobs) {
    this.blobs = blobs;
  }

  public void setAssociatedConferenceRoomTechnologies(List<ConferenceRoomTechnology> associatedConferenceRoomTechnologies) {
    this.associatedConferenceRoomTechnologies = associatedConferenceRoomTechnologies;
  }

  public void setAssociatedFoodBeverage(List<FoodBeverage> associatedFoodBeverage) {
    this.associatedFoodBeverage = associatedFoodBeverage;
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

  public String getDescriptionSv() {
    return descriptionSv;
  }

  public String getDescriptionEn() {
    return descriptionEn;
  }

  public Long getPlantId() {
    return plantId;
  }

  public Double getFullDayPrice() {
    return fullDayPrice;
  }

  public Double getPreNoonPrice() {
    return preNoonPrice;
  }

  public Double getAfterNoonPrice() {
    return afterNoonPrice;
  }

  public Boolean getShowSizeInsteadOfName() {
    return showSizeInsteadOfName;
  }

  public String getBeforeNoonHourStart() {
    return beforeNoonHourStart;
  }

  public String getBeforeNoonHourEnd() {
    return beforeNoonHourEnd;
  }

  public String getAfterNoonHourStart() {
    return afterNoonHourStart;
  }

  public String getAfterNoonHourEnd() {
    return afterNoonHourEnd;
  }

  public List<ConferenceRoomSeating> getDefaultSeating() {
    return defaultSeating;
  }

  public List<Long> getConferenceRoomBlob() {
    return conferenceRoomBlob;
  }

  public Long getState() {
    return state;
  }

  public List<Technology> getTechnologies() {
    return technologies;
  }

  public List<Seating> getSeats() {
    return seats;
  }

  public List<ConferenceRoomBlob> getBlobs() {
    return blobs;
  }

  public List<ConferenceRoomTechnology> getAssociatedConferenceRoomTechnologies() {
    return associatedConferenceRoomTechnologies;
  }

  public List<FoodBeverage> getAssociatedFoodBeverage() {
    return associatedFoodBeverage;
  }
}
