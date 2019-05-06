package com.example.admin.nakedorigins.screens.dto;

public class FarmCoffeeDTO {
  String farmName;
  String farmDescription;

  public FarmCoffeeDTO(String farmName, String farmDescription) {
    this.farmName = farmName;
    this.farmDescription = farmDescription;
  }

  public String getFarmName() {
    return farmName;
  }

  public void setFarmName(String farmName) {
    this.farmName = farmName;
  }

  public String getFarmDescription() {
    return farmDescription;
  }

  public void setFarmDescription(String farmDescription) {
    this.farmDescription = farmDescription;
  }
}
