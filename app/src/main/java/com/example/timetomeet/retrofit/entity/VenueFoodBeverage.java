package com.example.timetomeet.retrofit.entity;

import com.example.timetomeet.customview.adapters.SelectFoodRecyclerAdapter;
import com.google.gson.annotations.SerializedName;

public class VenueFoodBeverage {
  @SerializedName("id")
  private Long id;

  @SerializedName("foodBeverage")
  private Long foodBeverage;

  @SerializedName("plant")
  private Long plant;

  @SerializedName("price")
  private Double price;

  private boolean isSelected;
  private String selectedTime;
  private transient SelectFoodRecyclerAdapter.ViewHolder viewHolder;

  //----- Constructors -----//
  public VenueFoodBeverage() {
  }

  //----- Methods -----//
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("VenueFoodBeverage{");
    sb.append("id=").append(id);
    sb.append(", foodBeverage=").append(foodBeverage);
    sb.append(", plant=").append(plant);
    sb.append(", price=").append(price);
    sb.append('}');
    return sb.toString();
  }

  //----- Setters -----//
  public void setId(Long id) {
    this.id = id;
  }

  public void setFoodBeverage(Long foodBeverage) {
    this.foodBeverage = foodBeverage;
  }

  public void setPlant(Long plant) {
    this.plant = plant;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public void setSelected(boolean selected) {
    isSelected = selected;
  }

  public void setSelectedTime(String selectedTime) {
    this.selectedTime = selectedTime;
  }

  public void setViewHolder(SelectFoodRecyclerAdapter.ViewHolder viewHolder) {
    this.viewHolder = viewHolder;
  }

  //----- Getters -----//
  public Long getId() {
    return id;
  }

  public Long getFoodBeverage() {
    return foodBeverage;
  }

  public Long getPlant() {
    return plant;
  }

  public Double getPrice() {
    return price;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public String getSelectedTime() {
    return selectedTime;
  }

  public SelectFoodRecyclerAdapter.ViewHolder getViewHolder() {
    return viewHolder;
  }
}
