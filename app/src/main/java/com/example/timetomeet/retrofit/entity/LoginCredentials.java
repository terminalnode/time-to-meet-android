package com.example.timetomeet.retrofit.entity;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class LoginCredentials {
  @SerializedName("username")
  private String userName;

  @SerializedName("password")
  private String password;

  //----- Constructors -----//
  public LoginCredentials() {
  }

  //----- Methods -----//
  @NonNull
  @Override
  public String toString() {
    return String.format("<LoginCredentials username=%s>", userName);
  }

  //----- Setters -----//
  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  //----- Getters -----//
  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }
}
