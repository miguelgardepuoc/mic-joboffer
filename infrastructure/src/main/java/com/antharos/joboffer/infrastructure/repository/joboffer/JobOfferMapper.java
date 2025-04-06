package com.antharos.joboffer.infrastructure.repository.joboffer;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.JobOfferId;
import com.antharos.joboffer.domain.joboffer.Salary;
import org.springframework.stereotype.Component;

@Component
public class JobOfferMapper {

  public JobOffer toDomain(JobOfferEntity entity) {
    return new JobOffer(
        JobOfferId.of(entity.getId().toString()),
        entity.getDescription(),
        new Salary(entity.getSalary()),
        entity.getRemote().floatValue(),
        entity.getRequirement(),
        entity.isActive(),
        entity.getCreatedBy(),
        entity.getCreatedAt(),
        entity.getLastModifiedBy(),
        entity.getLastModifiedAt());
  }
}
