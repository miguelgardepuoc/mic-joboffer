package com.antharos.joboffer.infrastructure.in.dto.candidate;

import com.antharos.joboffer.domain.candidate.*;
import com.antharos.joboffer.domain.candidate.valueobject.CandidateId;
import com.antharos.joboffer.domain.candidate.valueobject.Name;
import com.antharos.joboffer.domain.candidate.valueobject.PersonalEmail;
import com.antharos.joboffer.domain.candidate.valueobject.Surname;
import com.antharos.joboffer.domain.joboffer.valueobject.JobOfferId;
import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
  CandidateResponse toCandidateResponse(Candidate candidate);

  SimpleCandidateResponse toSimpleCandidateResponse(Candidate candidate);

  List<SimpleCandidateResponse> toSimpleCandidatesResponse(List<Candidate> candidateList);

  default UUID map(CandidateId id) {
    return id == null ? null : UUID.fromString(id.getValueAsString());
  }

  default UUID map(JobOfferId id) {
    return id == null ? null : UUID.fromString(id.getValueAsString());
  }

  default String map(PersonalEmail personalEmail) {
    return personalEmail == null ? null : personalEmail.value();
  }

  default String map(Name name) {
    return name == null ? null : name.value();
  }

  default String map(Surname surname) {
    return surname == null ? null : surname.value();
  }
}
