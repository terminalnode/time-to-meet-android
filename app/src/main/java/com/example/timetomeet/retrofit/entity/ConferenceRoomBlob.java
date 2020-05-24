package com.example.timetomeet.retrofit.entity;

import com.example.timetomeet.retrofit.LocalizableDescription;
import com.example.timetomeet.retrofit.LocalizableName;
import com.google.gson.annotations.SerializedName;

public class ConferenceRoomBlob implements LocalizableName, LocalizableDescription {
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

  @SerializedName("server")
  private String server;

  @SerializedName("path")
  private String path;

  @SerializedName("fileName")
  private String fileName;

  @SerializedName("url")
  private String url;

  //----- Constructors -----//
  public ConferenceRoomBlob() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    return "ConferenceRoomBlob{" +
        "id=" + id +
        ", nameSv='" + nameSv + '\'' +
        ", nameEn='" + nameEn + '\'' +
        ", descriptionSv='" + descriptionSv + '\'' +
        ", descriptionEn='" + descriptionEn + '\'' +
        ", server='" + server + '\'' +
        ", path='" + path + '\'' +
        ", fileName='" + fileName + '\'' +
        ", url='" + url + '\'' +
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

  public void setServer(String server) {
    this.server = server;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public void setUrl(String url) {
    this.url = url;
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

  public String getServer() {
    return server;
  }

  public String getPath() {
    return path;
  }

  public String getFileName() {
    return fileName;
  }

  public String getUrl() {
    return url;
  }
}
