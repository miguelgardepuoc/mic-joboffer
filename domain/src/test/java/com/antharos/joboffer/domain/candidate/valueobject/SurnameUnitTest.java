package com.antharos.joboffer.domain.candidate.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SurnameUnitTest {

  @Test
  void validSurname_shouldCreateObject() {
    Surname surname = new Surname("Doe");
    assertEquals("Doe", surname.value());
  }

  @Test
  void nullSurname_shouldThrowException() {
    NullPointerException exception =
        assertThrows(NullPointerException.class, () -> new Surname(null));
    assertEquals("Surname cannot be null", exception.getMessage());
  }

  @Test
  void blankSurname_shouldThrowException() {
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new Surname("  "));
    assertEquals("Surname cannot be blank", exception.getMessage());
  }
}
