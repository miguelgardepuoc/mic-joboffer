package com.antharos.joboffer.infrastructure.out.event.model;

import com.antharos.joboffer.domain.candidate.Candidate;
import java.util.UUID;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CandidatePayloadMapper {

  default CandidatePayload toPayload(Candidate domain) {
    CandidatePayload payload = new CandidatePayload();
    payload.setId(UUID.fromString(domain.getId().getValueAsString()));
    payload.setJobOfferId(domain.getJobOfferId().getValueAsString());
    payload.setCvFilename(domain.getCvFilename());
    payload.setPersonalEmail(domain.getPersonalEmail().value());
    payload.setName(domain.getName() != null ? domain.getName().value() : null);
    payload.setSurname(domain.getSurname() != null ? domain.getSurname().value() : null);
    payload.setTelephoneNumber(
        domain.getTelephoneNumber() != null ? domain.getTelephoneNumber().value() : null);
    return payload;
  }
}
