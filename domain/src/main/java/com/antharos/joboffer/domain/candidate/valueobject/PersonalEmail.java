package com.antharos.joboffer.domain.candidate.valueobject;

import java.util.regex.Pattern;

public record PersonalEmail(String value) {
  private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

  public PersonalEmail {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException("Email cannot be null or empty");
    }
    if (!isValidEmail(value)) {
      throw new IllegalArgumentException("Invalid email format");
    }
  }

  private boolean isValidEmail(String email) {
    return Pattern.matches(EMAIL_REGEX, email);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PersonalEmail that = (PersonalEmail) o;
    return value.equals(that.value);
  }

  @Override
  public String toString() {
    return value;
  }
}
