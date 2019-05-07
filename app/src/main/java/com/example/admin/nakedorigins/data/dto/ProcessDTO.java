package com.example.admin.nakedorigins.data.dto;

public class ProcessDTO {
  String processName;
  String processStep;
  String processDescription;

  public ProcessDTO(String processName, String processStep, String processDescription) {
    this.processName = processName;
    this.processStep = processStep;
    this.processDescription = processDescription;
  }


  public String getProcessName() {
    return processName;
  }

  public void setProcessName(String processName) {
    this.processName = processName;
  }

  public String getProcessStep() {
    return processStep;
  }

  public void setProcessStep(String processStep) {
    this.processStep = processStep;
  }

  public String getProcessDescription() {
    return processDescription;
  }

  public void setProcessDescription(String processDescription) {
    this.processDescription = processDescription;
  }
}
