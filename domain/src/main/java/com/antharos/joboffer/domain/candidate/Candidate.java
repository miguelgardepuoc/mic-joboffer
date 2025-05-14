package com.antharos.joboffer.domain.candidate;

import static com.antharos.joboffer.domain.candidate.CandidateStatus.*;

import com.antharos.joboffer.domain.candidate.valueobject.*;
import com.antharos.joboffer.domain.globalexceptions.ConflictException;
import com.antharos.joboffer.domain.joboffer.valueobject.JobOfferId;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Candidate {

  private CandidateId id;

  private PersonalEmail personalEmail;

  private String cvFilename;

  private Name name;

  private Surname surname;

  private TelephoneNumber telephoneNumber;

  private CandidateStatus status;

  private String createdBy;

  private LocalDate createdAt;

  private String lastModifiedBy;

  private LocalDate lastModifiedAt;

  private JobOfferId jobOfferId;

  public Candidate(CandidateId id) {
    this.id = id;
  }

  public Candidate(
      CandidateId candidateId,
      PersonalEmail personalEmail,
      String cvFilename,
      CandidateStatus status,
      JobOfferId jobOfferId,
      String createdBy,
      LocalDate createdAt) {
    this.id = candidateId;
    this.personalEmail = personalEmail;
    this.cvFilename = cvFilename;
    this.status = status;
    this.jobOfferId = jobOfferId;
    this.createdBy = createdBy;
    this.createdAt = createdAt;
  }

  public static Candidate create(
      CandidateId candidateId,
      PersonalEmail personalEmail,
      String cv,
      JobOfferId jobOfferId,
      String createdBy) {
    return new Candidate(
        candidateId, personalEmail, cv, APPLIED, jobOfferId, createdBy, LocalDate.now());
  }

  public void reject(String byUser) {
    if (HIRED.equals(this.status) || REJECTED.equals(this.status)) {
      throw new ConflictException();
    }
    this.status = REJECTED;
    this.lastModifiedBy = byUser;
  }

  public void interview(String byUser) {
    if (HIRED.equals(this.status)
        || REJECTED.equals(this.status)
        || INTERVIEWING.equals(this.status)) {
      throw new ConflictException();
    }
    this.status = INTERVIEWING;
    this.lastModifiedBy = byUser;
  }

  public void hire(String byUser) {
    if (HIRED.equals(this.status) || REJECTED.equals(this.status)) {
      throw new ConflictException();
    }
    this.status = HIRED;
    this.lastModifiedBy = byUser;
  }

  public void fullname(Name name, Surname surname, String byUser) {
    this.name = name;
    this.surname = surname;
    this.lastModifiedBy = byUser;
  }
}
