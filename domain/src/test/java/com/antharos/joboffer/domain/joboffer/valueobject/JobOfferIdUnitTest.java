package com.antharos.joboffer.domain.joboffer.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import com.antharos.joboffer.domain.joboffer.exception.InvalidJobOfferException;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class JobOfferIdUnitTest {

  @Test
  void whenValidUUIDString_thenJobOfferIdIsCreated() {
    String validUUID = UUID.randomUUID().toString();
    JobOfferId jobOfferId = JobOfferId.of(validUUID);

    assertNotNull(jobOfferId);
    assertEquals(validUUID, jobOfferId.getValueAsString());
  }

  @Test
  void whenInvalidUUIDString_thenThrowInvalidDepartmentException() {
    String invalidUUID = "not-a-uuid";

    InvalidJobOfferException exception =
        assertThrows(InvalidJobOfferException.class, () -> JobOfferId.of(invalidUUID));

    assertTrue(exception.getMessage().contains("JobOfferId must be a valid UUID"));
    assertTrue(exception.getMessage().contains(invalidUUID));
  }
}
