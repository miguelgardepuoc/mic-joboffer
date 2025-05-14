package com.antharos.joboffer.domain.candidate.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PersonalEmailUnitTest {

  @Test
  void validEmail_createsObjectSuccessfully() {
    String emailValue = "john.doe@example.com";
    PersonalEmail email = new PersonalEmail(emailValue);

    assertEquals(emailValue, email.value());
    assertEquals(emailValue, email.toString());
  }

  @Test
  void nullEmail_throwsIllegalArgumentException() {
    Exception exception =
        assertThrows(IllegalArgumentException.class, () -> new PersonalEmail(null));
    assertEquals("Email cannot be null or empty", exception.getMessage());
  }

  @Test
  void emptyEmail_throwsIllegalArgumentException() {
    Exception exception =
        assertThrows(IllegalArgumentException.class, () -> new PersonalEmail(" "));
    assertEquals("Email cannot be null or empty", exception.getMessage());
  }

  @Test
  void invalidEmailFormat_throwsIllegalArgumentException() {
    String[] invalidEmails = {"plainaddress", "@missinguser.com"};

    for (String invalid : invalidEmails) {
      Exception exception =
          assertThrows(IllegalArgumentException.class, () -> new PersonalEmail(invalid));
      assertEquals("Invalid email format", exception.getMessage(), "Failed for: " + invalid);
    }
  }

  @Test
  void equals_returnsTrueForSameValue() {
    PersonalEmail email1 = new PersonalEmail("test@example.com");
    PersonalEmail email2 = new PersonalEmail("test@example.com");

    assertEquals(email1, email2);
    assertEquals(email1.hashCode(), email2.hashCode());
  }

  @Test
  void equals_returnsFalseForDifferentValues() {
    PersonalEmail email1 = new PersonalEmail("first@example.com");
    PersonalEmail email2 = new PersonalEmail("second@example.com");

    assertNotEquals(email1, email2);
  }

  @Test
  void toString_returnsEmailValue() {
    String value = "someone@example.com";
    PersonalEmail email = new PersonalEmail(value);

    assertEquals(value, email.toString());
  }
}
