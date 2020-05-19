package com.example.timetomeet.retrofit.entity;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Credentials {
  @SerializedName("username")
  private String username;

  @SerializedName("password")
  private String password;

  //----- Constructors -----//
  public Credentials() {
  }

  //----- Methods -----//
  @NonNull
  @Override
  public String toString() {
    return String.format("LoginCredentials{username=%s}", username);
  }

  //----- Setters -----//
  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  //----- Getters -----//
  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
