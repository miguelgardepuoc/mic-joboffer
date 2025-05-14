package com.antharos.joboffer.domain.candidate.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TelephoneNumberUnitTest {

  @Test
  void validTelephoneNumber_shouldCreateObject() {
    TelephoneNumber number = new TelephoneNumber("+1234567890");
    assertEquals("+1234567890", number.value());
  }

  @Test
  void nullTelephoneNumber_shouldThrowException() {
    Exception exception = assertThrows(NullPointerException.class, () -> new TelephoneNumber(null));
    assertEquals("Telephone number cannot be null", exception.getMessage());
  }

  @Test
  void invalidTelephoneNumber_shouldThrowException() {
    String[] invalidNumbers = {"123", "abc", "+123abc", "123-456", "++123456789", ""};

    for (String number : invalidNumbers) {
      IllegalArgumentException exception =
          assertThrows(
              IllegalArgumentException.class,
              () -> new TelephoneNumber(number),
              "Failed on input: " + number);
      assertEquals("Invalid telephone number format", exception.getMessage());
    }
  }
}
