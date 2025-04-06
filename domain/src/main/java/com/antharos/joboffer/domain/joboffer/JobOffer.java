package com.antharos.joboffer.domain.joboffer;

import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class JobOffer {

  private final JobOfferId id;

  private String description;

  private Salary salary;

  private Float remote;

  private String requirement;

  private boolean isActive;

  private String createdBy;

  private Date createdAt;

  private String lastModifiedBy;

  private Date lastModifiedAt;

  public JobOffer(JobOfferId id) {
    this.id = id;
  }
}
