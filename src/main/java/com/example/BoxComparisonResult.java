package com.example;

public enum BoxComparisonResult {
  EQUAL("Boxes are equal"),
  FIRST_SMALLER("The first box is smaller than the second one"),
  FIRST_LARGER("The first box is larger than the second one"),
  INCOMPARABLE("Boxes are incomparable");

  private final String message;

  BoxComparisonResult(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return message;
  }
}