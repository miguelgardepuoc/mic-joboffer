package com.antharos.joboffer.infrastructure.out.repository.joboffer;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.valueobject.JobOfferId;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class JobOfferMapper {

  public JobOffer toDomain(JobOfferEntity entity) {
    return new JobOffer(
        JobOfferId.of(entity.getId().toString()),
        entity.getJobTitleId(),
        entity.getDescription(),
        entity.getSalaryRange().toDomain(),
        entity.getRemote(),
        entity.getRequirement(),
        entity.isActive(),
        entity.getCreatedBy(),
        entity.getCreatedAt(),
        entity.getLastModifiedBy(),
        entity.getLastModifiedAt());
  }

  public JobOfferEntity toEntity(JobOffer jobOffer) {
    JobOfferEntity entity = new JobOfferEntity();
    entity.setId(UUID.fromString(jobOffer.getId().getValueAsString()));
    entity.setJobTitleId(jobOffer.getJobTitleId());
    entity.setActive(jobOffer.isActive());
    // entity.setCandidates();
    entity.setDescription(jobOffer.getDescription());
    entity.setRequirement(jobOffer.getRequirement());
    entity.setRemote(jobOffer.getRemote());
    entity.setSalaryRange(SalaryRangeEmbeddable.fromDomain(jobOffer.getSalaryRange()));
    entity.setCreatedAt(jobOffer.getCreatedAt());
    entity.setCreatedBy(jobOffer.getCreatedBy());
    entity.setLastModifiedAt(jobOffer.getLastModifiedAt());
    entity.setLastModifiedBy(jobOffer.getLastModifiedBy());
    return entity;
  }
}
