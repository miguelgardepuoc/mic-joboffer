package com.antharos.joboffer.domain.candidate.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import com.antharos.joboffer.domain.candidate.exception.InvalidCandidateException;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class CandidateIdUnitTest {

  @Test
  void whenValidUUIDString_thenJobOfferIdIsCreated() {
    String validUUID = UUID.randomUUID().toString();
    CandidateId candidateId = CandidateId.of(validUUID);

    assertNotNull(candidateId);
    assertEquals(validUUID, candidateId.getValueAsString());
  }

  @Test
  void whenInvalidUUIDString_thenThrowInvalidDepartmentException() {
    String invalidUUID = "not-a-uuid";

    InvalidCandidateException exception =
        assertThrows(InvalidCandidateException.class, () -> CandidateId.of(invalidUUID));

    assertTrue(exception.getMessage().contains("CandidateId must be a valid UUID"));
    assertTrue(exception.getMessage().contains(invalidUUID));
  }
}
