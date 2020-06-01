package com.example.timetomeet.retrofit.entity;

import com.example.timetomeet.retrofit.LocalizableName;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Venue implements LocalizableName {
  @SerializedName("id")
  private Long id;

  @SerializedName("name")
  private String nameSv;

  @SerializedName("name_en")
  private String nameEn;

  @SerializedName("nameForUrl")
  private String nameForUrl;

  @SerializedName("contact_person_name")
  private String contactPersonName;

  @SerializedName("contact_person_email")
  private String contactPersonEmail;

  @SerializedName("contact_person_phone")
  private String contactPersonPhone;

  @SerializedName("organization")
  private Long organization;

  @SerializedName("plantType")
  private Long plantType;

  @SerializedName("receptionOpenMon")
  private Boolean receptionOpenMon;

  @SerializedName("receptionOpenTue")
  private Boolean receptionOpenTue;

  @SerializedName("receptionOpenWed")
  private Boolean receptionOpenWed;

  @SerializedName("receptionOpenThur")
  private Boolean receptionOpenThur;

  @SerializedName("receptionOpenFri")
  private Boolean receptionOpenFri;

  @SerializedName("receptionOpenSat")
  private Boolean receptionOpenSat;

  @SerializedName("receptionOpenSun")
  private Boolean receptionOpenSun;

  @SerializedName("factsAboutPlant")
  private String factsAboutPlantSv;

  @SerializedName("factsAboutPlant_en")
  private String factsAboutPlantEn;

  @SerializedName("facilities")
  private String facilitiesSv;

  @SerializedName("facilities_en")
  private String facilitiesEn;

  @SerializedName("numberOfParkingSpots")
  private Long numberOfParkingSpots;

  @SerializedName("accessibility")
  private String accessibilitySv;

  @SerializedName("accessibility_en")
  private String accessibilityEn;

  @SerializedName("visitingAddress")
  private Long visitingAddress;

  @SerializedName("confirmation_person")
  private String confirmationPerson;

  @SerializedName("plantState")
  private Long plantState;

  @SerializedName("state")
  private Long state;

  @SerializedName("confirmation_email")
  private String confirmationEmail;

  @SerializedName("confirmation_phone")
  private String confirmationPhone;

  @SerializedName("bookableSaturdays")
  private Boolean bookableSaturdays;

  @SerializedName("bookableSundays")
  private Boolean bookableSundays;

  @SerializedName("plantFoodBeverages")
  private List<FoodBeverage> plantFoodBeverages;

  @SerializedName("technologies")
  private List<Technology> technologies;

  @SerializedName("seats")
  private List<Seating> seats;

  @SerializedName("blobs")
  private List<ConferenceRoomBlob> blobs;

  @SerializedName("rating")
  private Long rating;

  private List<VenueFoodBeverage> associatedFoodBeverages;

  //----- Constructors -----//
  public Venue() {
  }

  //----- Methods -----//

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Venue{");
    sb.append("id=").append(id);
    sb.append(", nameSv='").append(nameSv).append('\'');
    sb.append(", nameEn='").append(nameEn).append('\'');
    sb.append(", nameForUrl='").append(nameForUrl).append('\'');
    sb.append(", contactPersonName='").append(contactPersonName).append('\'');
    sb.append(", contactPersonEmail='").append(contactPersonEmail).append('\'');
    sb.append(", contactPersonPhone='").append(contactPersonPhone).append('\'');
    sb.append(", organization=").append(organization);
    sb.append(", plantType=").append(plantType);
    sb.append(", receptionOpenMon=").append(receptionOpenMon);
    sb.append(", receptionOpenTue=").append(receptionOpenTue);
    sb.append(", receptionOpenWed=").append(receptionOpenWed);
    sb.append(", receptionOpenThur=").append(receptionOpenThur);
    sb.append(", receptionOpenFri=").append(receptionOpenFri);
    sb.append(", receptionOpenSat=").append(receptionOpenSat);
    sb.append(", receptionOpenSun=").append(receptionOpenSun);
    sb.append(", factsAboutPlantSv='").append(factsAboutPlantSv).append('\'');
    sb.append(", factsAboutPlantEn='").append(factsAboutPlantEn).append('\'');
    sb.append(", facilitiesSv='").append(facilitiesSv).append('\'');
    sb.append(", facilitiesEn='").append(facilitiesEn).append('\'');
    sb.append(", numberOfParkingSpots=").append(numberOfParkingSpots);
    sb.append(", accessibilitySv='").append(accessibilitySv).append('\'');
    sb.append(", accessibilityEn='").append(accessibilityEn).append('\'');
    sb.append(", visitingAddress=").append(visitingAddress);
    sb.append(", confirmationPerson='").append(confirmationPerson).append('\'');
    sb.append(", plantState=").append(plantState);
    sb.append(", state=").append(state);
    sb.append(", confirmationEmail='").append(confirmationEmail).append('\'');
    sb.append(", confirmationPhone='").append(confirmationPhone).append('\'');
    sb.append(", bookableSaturdays=").append(bookableSaturdays);
    sb.append(", bookableSundays=").append(bookableSundays);
    sb.append(", plantFoodBeverages=").append(plantFoodBeverages);
    sb.append(", technologies=").append(technologies);
    sb.append(", seats=").append(seats);
    sb.append(", blobs=").append(blobs);
    sb.append(", rating=").append(rating);
    sb.append('}');
    return sb.toString();
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

  public void setNameForUrl(String nameForUrl) {
    this.nameForUrl = nameForUrl;
  }

  public void setContactPersonName(String contactPersonName) {
    this.contactPersonName = contactPersonName;
  }

  public void setContactPersonEmail(String contactPersonEmail) {
    this.contactPersonEmail = contactPersonEmail;
  }

  public void setContactPersonPhone(String contactPersonPhone) {
    this.contactPersonPhone = contactPersonPhone;
  }

  public void setOrganization(Long organization) {
    this.organization = organization;
  }

  public void setPlantType(Long plantType) {
    this.plantType = plantType;
  }

  public void setReceptionOpenMon(Boolean receptionOpenMon) {
    this.receptionOpenMon = receptionOpenMon;
  }

  public void setReceptionOpenTue(Boolean receptionOpenTue) {
    this.receptionOpenTue = receptionOpenTue;
  }

  public void setReceptionOpenWed(Boolean receptionOpenWed) {
    this.receptionOpenWed = receptionOpenWed;
  }

  public void setReceptionOpenThur(Boolean receptionOpenThur) {
    this.receptionOpenThur = receptionOpenThur;
  }

  public void setReceptionOpenFri(Boolean receptionOpenFri) {
    this.receptionOpenFri = receptionOpenFri;
  }

  public void setReceptionOpenSat(Boolean receptionOpenSat) {
    this.receptionOpenSat = receptionOpenSat;
  }

  public void setReceptionOpenSun(Boolean receptionOpenSun) {
    this.receptionOpenSun = receptionOpenSun;
  }

  public void setFactsAboutPlantSv(String factsAboutPlantSv) {
    this.factsAboutPlantSv = factsAboutPlantSv;
  }

  public void setFactsAboutPlantEn(String factsAboutPlantEn) {
    this.factsAboutPlantEn = factsAboutPlantEn;
  }

  public void setFacilitiesSv(String facilitiesSv) {
    this.facilitiesSv = facilitiesSv;
  }

  public void setFacilitiesEn(String facilitiesEn) {
    this.facilitiesEn = facilitiesEn;
  }

  public void setNumberOfParkingSpots(Long numberOfParkingSpots) {
    this.numberOfParkingSpots = numberOfParkingSpots;
  }

  public void setAccessibilitySv(String accessibilitySv) {
    this.accessibilitySv = accessibilitySv;
  }

  public void setAccessibilityEn(String accessibilityEn) {
    this.accessibilityEn = accessibilityEn;
  }

  public void setVisitingAddress(Long visitingAddress) {
    this.visitingAddress = visitingAddress;
  }

  public void setConfirmationPerson(String confirmationPerson) {
    this.confirmationPerson = confirmationPerson;
  }

  public void setPlantState(Long plantState) {
    this.plantState = plantState;
  }

  public void setState(Long state) {
    this.state = state;
  }

  public void setConfirmationEmail(String confirmationEmail) {
    this.confirmationEmail = confirmationEmail;
  }

  public void setConfirmationPhone(String confirmationPhone) {
    this.confirmationPhone = confirmationPhone;
  }

  public void setBookableSaturdays(Boolean bookableSaturdays) {
    this.bookableSaturdays = bookableSaturdays;
  }

  public void setBookableSundays(Boolean bookableSundays) {
    this.bookableSundays = bookableSundays;
  }

  public void setPlantFoodBeverages(List<FoodBeverage> plantFoodBeverages) {
    this.plantFoodBeverages = plantFoodBeverages;
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

  public void setRating(Long rating) {
    this.rating = rating;
  }

  public void setAssociatedFoodBeverages(List<VenueFoodBeverage> associatedFoodBeverages) {
    this.associatedFoodBeverages = associatedFoodBeverages;
  }

  //----- Getters -----//
  public Long getId() {
    return id;
  }

  @Override
  public String getNameSv() {
    return nameSv;
  }

  @Override
  public String getNameEn() {
    return nameEn;
  }

  public String getNameForUrl() {
    return nameForUrl;
  }

  public String getContactPersonName() {
    return contactPersonName;
  }

  public String getContactPersonEmail() {
    return contactPersonEmail;
  }

  public String getContactPersonPhone() {
    return contactPersonPhone;
  }

  public Long getOrganization() {
    return organization;
  }

  public Long getPlantType() {
    return plantType;
  }

  public Boolean getReceptionOpenMon() {
    return receptionOpenMon;
  }

  public Boolean getReceptionOpenTue() {
    return receptionOpenTue;
  }

  public Boolean getReceptionOpenWed() {
    return receptionOpenWed;
  }

  public Boolean getReceptionOpenThur() {
    return receptionOpenThur;
  }

  public Boolean getReceptionOpenFri() {
    return receptionOpenFri;
  }

  public Boolean getReceptionOpenSat() {
    return receptionOpenSat;
  }

  public Boolean getReceptionOpenSun() {
    return receptionOpenSun;
  }

  public String getFactsAboutPlantSv() {
    return factsAboutPlantSv;
  }

  public String getFactsAboutPlantEn() {
    return factsAboutPlantEn;
  }

  public String getFacilitiesSv() {
    return facilitiesSv;
  }

  public String getFacilitiesEn() {
    return facilitiesEn;
  }

  public Long getNumberOfParkingSpots() {
    return numberOfParkingSpots;
  }

  public String getAccessibilitySv() {
    return accessibilitySv;
  }

  public String getAccessibilityEn() {
    return accessibilityEn;
  }

  public Long getVisitingAddress() {
    return visitingAddress;
  }

  public String getConfirmationPerson() {
    return confirmationPerson;
  }

  public Long getPlantState() {
    return plantState;
  }

  public Long getState() {
    return state;
  }

  public String getConfirmationEmail() {
    return confirmationEmail;
  }

  public String getConfirmationPhone() {
    return confirmationPhone;
  }

  public Boolean getBookableSaturdays() {
    return bookableSaturdays;
  }

  public Boolean getBookableSundays() {
    return bookableSundays;
  }

  public List<FoodBeverage> getPlantFoodBeverages() {
    return plantFoodBeverages;
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

  public Long getRating() {
    return rating;
  }

  public List<VenueFoodBeverage> getAssociatedFoodBeverages() {
    return associatedFoodBeverages;
  }
}
