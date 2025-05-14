package com.antharos.joboffer.domain.candidate.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NameUnitTest {

  @Test
  void validName_shouldCreateObject() {
    Name name = new Name("John");
    assertEquals("John", name.value());
  }

  @Test
  void nullName_shouldThrowException() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> new Name(null));
    assertEquals("Name cannot be null", exception.getMessage());
  }

  @Test
  void blankName_shouldThrowException() {
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new Name(" "));
    assertEquals("Name cannot be blank", exception.getMessage());
  }
}
