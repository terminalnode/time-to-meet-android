package com.example.timetomeet.retrofit.entity;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Token {
  @SerializedName("token")
  private String token;

  //----- Constructors -----//
  public Token() {
  }

  //----- Methods -----//
  @NonNull
  @Override
  public String toString() {
    return String.format("<Token token=%s>", token);
  }

  //----- Setters -----//
  public void setToken(String token) {
    this.token = token;
  }

  //----- Getters -----//
  public String getToken() {
    return token;
  }
}
