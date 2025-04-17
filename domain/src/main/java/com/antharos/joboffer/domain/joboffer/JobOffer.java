package com.antharos.joboffer.domain.joboffer;

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

  public static JobOffer create(JobOfferId id, UUID jobTitleId, String description, SalaryRange salaryRange, short remote, String requirement, String createdBy) {
    return new JobOffer(id, jobTitleId, description, salaryRange, remote,requirement, true, createdBy, new Date(), null, null);
  }
}
