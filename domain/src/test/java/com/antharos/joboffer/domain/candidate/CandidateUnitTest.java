package com.antharos.joboffer.domain.candidate;

import static com.antharos.joboffer.domain.candidate.CandidateStatus.*;
import static org.junit.jupiter.api.Assertions.*;

import com.antharos.joboffer.domain.candidate.valueobject.*;
import com.antharos.joboffer.domain.globalexceptions.ConflictException;
import com.antharos.joboffer.domain.joboffer.valueobject.JobOfferId;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CandidateUnitTest {

  private Candidate candidate;
  private CandidateId candidateId;
  private PersonalEmail email;
  private String createdBy;
  private JobOfferId jobOfferId;

  @BeforeEach
  void setUp() {
    candidateId = CandidateId.of(UUID.randomUUID().toString());
    email = new PersonalEmail("test@example.com");
    createdBy = "admin";
    jobOfferId = JobOfferId.of(UUID.randomUUID().toString());

    candidate = Candidate.create(candidateId, email, "cv.pdf", jobOfferId, createdBy);
  }

  @Test
  void createCandidate_setsInitialStateCorrectly() {
    assertEquals(candidateId, candidate.getId());
    assertEquals(email, candidate.getPersonalEmail());
    assertEquals("cv.pdf", candidate.getCvFilename());
    assertEquals(APPLIED, candidate.getStatus());
    assertEquals(jobOfferId, candidate.getJobOfferId());
    assertEquals(createdBy, candidate.getCreatedBy());
    assertNotNull(candidate.getCreatedAt());
  }

  @Test
  void hireCandidate_changesStatusToHired() {
    candidate.hire("admin");
    assertEquals(HIRED, candidate.getStatus());
    assertEquals("admin", candidate.getLastModifiedBy());
  }

  @Test
  void hireCandidate_whenAlreadyHired_throwsConflictException() {
    candidate.hire("admin");
    assertThrows(ConflictException.class, () -> candidate.hire("admin"));
  }

  @Test
  void hireCandidate_whenRejected_throwsConflictException() {
    candidate.reject("admin");
    assertThrows(ConflictException.class, () -> candidate.hire("admin"));
  }

  @Test
  void rejectCandidate_changesStatusToRejected() {
    candidate.reject("admin");
    assertEquals(REJECTED, candidate.getStatus());
    assertEquals("admin", candidate.getLastModifiedBy());
  }

  @Test
  void rejectCandidate_whenAlreadyRejected_throwsConflictException() {
    candidate.reject("admin");
    assertThrows(ConflictException.class, () -> candidate.reject("admin"));
  }

  @Test
  void interviewCandidate_changesStatusToInterviewing() {
    candidate.interview("admin");
    assertEquals(INTERVIEWING, candidate.getStatus());
    assertEquals("admin", candidate.getLastModifiedBy());
  }

  @Test
  void interviewCandidate_whenAlreadyInterviewing_throwsConflictException() {
    candidate.interview("admin");
    assertThrows(ConflictException.class, () -> candidate.interview("admin"));
  }

  @Test
  void interviewCandidate_whenRejected_throwsConflictException() {
    candidate.reject("admin");
    assertThrows(ConflictException.class, () -> candidate.interview("admin"));
  }

  @Test
  void interviewCandidate_whenHired_throwsConflictException() {
    candidate.hire("admin");
    assertThrows(ConflictException.class, () -> candidate.interview("admin"));
  }

  @Test
  void fullname_setsNameAndSurname() {
    Name name = new Name("John");
    Surname surname = new Surname("Doe");

    candidate.fullname(name, surname, "admin");

    assertEquals(name, candidate.getName());
    assertEquals(surname, candidate.getSurname());
    assertEquals("admin", candidate.getLastModifiedBy());
  }
}
