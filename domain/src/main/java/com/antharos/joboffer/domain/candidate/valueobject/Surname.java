package com.antharos.joboffer.domain.candidate.valueobject;

import java.util.Objects;

public record Surname(String value) {
  public Surname {
    Objects.requireNonNull(value, "Surname cannot be null");
    if (value.isBlank()) {
      throw new IllegalArgumentException("Surname cannot be blank");
    }
  }
}
