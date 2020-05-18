package com.example.timetomeet.retrofit.entity;

import com.google.gson.annotations.SerializedName;

public class User {
  @SerializedName("first_name")
  private String firstName;

  @SerializedName("last_name")
  private String lastName;

  @SerializedName("username")
  private String username;

  @SerializedName("password")
  private String password;

  @SerializedName("email")
  private String email;

  @SerializedName("phone_number")
  private String phoneNumber;

  @SerializedName("organization_name")
  private String organizationName;

  @SerializedName("organization_nr")
  private String organizaionNumber;

  @SerializedName("street")
  private String street;

  @SerializedName("city_name")
  private String cityName;

  @SerializedName("zipCode")
  private String zipCode;

  //----- Constructors -----//
  public User() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("User{");
    sb.append("firstName='").append(firstName).append('\'');
    sb.append(", lastName='").append(lastName).append('\'');
    sb.append(", username='").append(username).append('\'');
    sb.append(", password='").append(password).append('\'');
    sb.append(", email='").append(email).append('\'');
    sb.append(", phoneNumber='").append(phoneNumber).append('\'');
    sb.append(", organizationName='").append(organizationName).append('\'');
    sb.append(", organizaionNumber='").append(organizaionNumber).append('\'');
    sb.append(", street='").append(street).append('\'');
    sb.append(", cityName='").append(cityName).append('\'');
    sb.append(", zipCode='").append(zipCode).append('\'');
    sb.append('}');
    return sb.toString();
  }

  //----- Setters -----//
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
  }

  public void setOrganizaionNumber(String organizaionNumber) {
    this.organizaionNumber = organizaionNumber;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  //----- Getters -----//
  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getOrganizationName() {
    return organizationName;
  }

  public String getOrganizaionNumber() {
    return organizaionNumber;
  }

  public String getStreet() {
    return street;
  }

  public String getCityName() {
    return cityName;
  }

  public String getZipCode() {
    return zipCode;
  }
}