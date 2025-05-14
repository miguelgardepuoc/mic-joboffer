package com.antharos.joboffer.infrastructure.out.repository.candidate;

import com.antharos.joboffer.domain.candidate.*;
import com.antharos.joboffer.domain.candidate.valueobject.*;
import com.antharos.joboffer.domain.joboffer.valueobject.JobOfferId;
import com.antharos.joboffer.infrastructure.out.repository.joboffer.JobOfferEntity;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper {

  public Candidate toDomain(CandidateEntity entity) {
    PersonalEmail email = new PersonalEmail(entity.getPersonalEmail());

    Name name = null;
    if (entity.getName() != null) {
      name = new Name(entity.getName());
    }

    Surname surname = null;
    if (entity.getSurname() != null) {
      surname = new Surname(entity.getSurname());
    }

    TelephoneNumber phoneNumber = null;
    if (entity.getPhoneNumber() != null) {
      phoneNumber = new TelephoneNumber(entity.getPhoneNumber());
    }

    return new Candidate(
        CandidateId.of(entity.getId().toString()),
        email,
        entity.getCvFilename(),
        name,
        surname,
        phoneNumber,
        entity.getStatus(),
        entity.getCreatedBy(),
        entity.getCreatedAt(),
        entity.getLastModifiedBy(),
        entity.getLastModifiedAt(),
        JobOfferId.of(entity.getJobOffer().getId().toString()));
  }

  public CandidateEntity toEntity(Candidate domain) {
    CandidateEntity entity = new CandidateEntity();

    entity.setId(UUID.fromString(domain.getId().getValueAsString()));
    entity.setPersonalEmail(domain.getPersonalEmail().value());
    entity.setCvFilename(domain.getCvFilename());
    entity.setName(domain.getName() != null ? domain.getName().value() : null);
    entity.setSurname(domain.getSurname() != null ? domain.getSurname().value() : null);
    entity.setPhoneNumber(
        domain.getTelephoneNumber() != null ? domain.getTelephoneNumber().value() : null);
    entity.setStatus(CandidateStatus.valueOf(domain.getStatus().name()));
    entity.setCreatedBy(domain.getCreatedBy());
    entity.setCreatedAt(domain.getCreatedAt());
    entity.setLastModifiedBy(domain.getLastModifiedBy());
    entity.setLastModifiedAt(domain.getLastModifiedAt());
    entity.setJobOffer(
        new JobOfferEntity(UUID.fromString(domain.getJobOfferId().getValueAsString())));

    return entity;
  }
}
