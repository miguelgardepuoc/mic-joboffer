package com.antharos.joboffer.domain.candidate;

import static com.antharos.joboffer.domain.candidate.CandidateStatus.APPLIED;

import com.antharos.joboffer.domain.joboffer.JobOfferId;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Candidate {

  private CandidateId id;

  private PersonalEmail personalEmail;

  private String cvUrl;

  private Name name;

  private Surname surname;

  private TelephoneNumber telephoneNumber;

  private CandidateStatus status;

  private String createdBy;

  private Date createdAt;

  private String lastModifiedBy;

  private Date lastModifiedAt;

  private JobOfferId jobOfferId;

  public Candidate(CandidateId id) {
    this.id = id;
  }

  public Candidate(
      CandidateId candidateId,
      PersonalEmail personalEmail,
      String cvUrl,
      CandidateStatus status,
      JobOfferId jobOfferId,
      String createdBy) {
    this.id = candidateId;
    this.personalEmail = personalEmail;
    this.cvUrl = cvUrl;
    this.status = status;
    this.jobOfferId = jobOfferId;
    this.createdBy = createdBy;
  }

  public static Candidate create(
      CandidateId candidateId,
      PersonalEmail personalEmail,
      String cv,
      JobOfferId jobOfferId,
      String createdBy) {
    return new Candidate(candidateId, personalEmail, cv, APPLIED, jobOfferId, createdBy);
  }
}
