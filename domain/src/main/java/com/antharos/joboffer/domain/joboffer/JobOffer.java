package com.antharos.joboffer.domain.joboffer;

import com.antharos.joboffer.domain.globalexceptions.ConflictException;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class JobOffer {

  private final JobOfferId id;

  private UUID jobTitleId;

  private String description;

  private SalaryRange salaryRange;

  private short remote;

  private String requirement;

  private boolean isActive;

  private String createdBy;

  private Date createdAt;

  private String lastModifiedBy;

  private Date lastModifiedAt;

  public static JobOffer create(
      JobOfferId id,
      UUID jobTitleId,
      String description,
      SalaryRange salaryRange,
      short remote,
      String requirement,
      String createdBy) {
    return new JobOffer(
        id,
        jobTitleId,
        description,
        salaryRange,
        remote,
        requirement,
        true,
        createdBy,
        new Date(),
        null,
        null);
  }

  public void update(
      String description,
      SalaryRange salaryRange,
      short remote,
      String requirement,
      String lastModifiedBy) {
    if (isJobOfferWithdrawn()) {
      throw new ConflictException();
    }
    this.description = description;
    this.salaryRange = salaryRange;
    this.remote = remote;
    this.requirement = requirement;
    this.lastModifiedBy = lastModifiedBy;
    this.lastModifiedAt = new Date();
  }

  public void withdraw(String lastModifiedBy) {
    if (isJobOfferWithdrawn()) {
      throw new ConflictException();
    }
    this.isActive = false;
    this.lastModifiedBy = lastModifiedBy;
  }

  public boolean isJobOfferWithdrawn() {
    return !this.isActive;
  }
}
