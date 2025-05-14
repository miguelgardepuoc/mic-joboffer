package com.antharos.joboffer.domain.joboffer;

import static org.junit.jupiter.api.Assertions.*;

import com.antharos.joboffer.domain.globalexceptions.ConflictException;
import com.antharos.joboffer.domain.joboffer.valueobject.JobOfferId;
import com.antharos.joboffer.domain.joboffer.valueobject.SalaryRange;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JobOfferUnitTest {

  private JobOffer jobOffer;
  private JobOfferId jobOfferId;
  private UUID jobTitleId;
  private String createdBy;
  private SalaryRange initialSalaryRange;

  @BeforeEach
  void setUp() {
    jobOfferId = JobOfferId.of(UUID.randomUUID().toString());
    jobTitleId = UUID.randomUUID();
    createdBy = "hr_admin";
    initialSalaryRange = new SalaryRange(new BigDecimal("50000"), new BigDecimal("70000"));

    jobOffer =
        JobOffer.create(
            jobOfferId,
            jobTitleId,
            "Backend Developer position",
            initialSalaryRange,
            (short) 1,
            "5+ years Java experience",
            createdBy);
  }

  @Test
  void createJobOffer_setsAllFieldsCorrectly() {
    assertEquals(jobOfferId, jobOffer.getId());
    assertEquals(jobTitleId, jobOffer.getJobTitleId());
    assertEquals("Backend Developer position", jobOffer.getDescription());
    assertEquals(initialSalaryRange, jobOffer.getSalaryRange());
    assertEquals(1, jobOffer.getRemote());
    assertEquals("5+ years Java experience", jobOffer.getRequirement());
    assertTrue(jobOffer.isActive());
    assertEquals(createdBy, jobOffer.getCreatedBy());
    assertNotNull(jobOffer.getCreatedAt());
  }

  @Test
  void updateJobOffer_updatesFieldsCorrectly() {
    SalaryRange updatedSalaryRange =
        new SalaryRange(new BigDecimal("60000"), new BigDecimal("80000"));
    jobOffer.update(
        "Updated description",
        updatedSalaryRange,
        (short) 0,
        "Spring Boot expertise",
        "editor_user");

    assertEquals("Updated description", jobOffer.getDescription());
    assertEquals(updatedSalaryRange, jobOffer.getSalaryRange());
    assertEquals(0, jobOffer.getRemote());
    assertEquals("Spring Boot expertise", jobOffer.getRequirement());
    assertEquals("editor_user", jobOffer.getLastModifiedBy());
  }

  @Test
  void updateWithdrawnJobOffer_throwsConflictException() {
    jobOffer.withdraw("admin_user");
    assertThrows(
        ConflictException.class,
        () ->
            jobOffer.update(
                "irrelevant", initialSalaryRange, (short) 0, "irrelevant", "another_user"));
  }

  @Test
  void withdrawJobOffer_setsIsActiveToFalse() {
    jobOffer.withdraw("admin_user");

    assertFalse(jobOffer.isActive());
    assertEquals("admin_user", jobOffer.getLastModifiedBy());
  }

  @Test
  void withdrawAlreadyWithdrawnJobOffer_throwsConflictException() {
    jobOffer.withdraw("admin_user");
    assertThrows(ConflictException.class, () -> jobOffer.withdraw("admin_user_again"));
  }

  @Test
  void isJobOfferWithdrawn_returnsTrueWhenInactive() {
    jobOffer.withdraw("admin_user");
    assertTrue(jobOffer.isJobOfferWithdrawn());
  }

  @Test
  void isJobOfferWithdrawn_returnsFalseWhenActive() {
    assertFalse(jobOffer.isJobOfferWithdrawn());
  }
}
