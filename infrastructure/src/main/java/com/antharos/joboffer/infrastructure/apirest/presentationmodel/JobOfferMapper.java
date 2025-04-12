package com.antharos.joboffer.infrastructure.apirest.presentationmodel;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.JobOfferId;
import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobOfferMapper {

  @Mapping(source = "salaryRange.min", target = "minSalary")
  @Mapping(source = "salaryRange.max", target = "maxSalary")
  SimpleJobOffer toSimpleJobOffer(JobOffer jobOffer);

  List<SimpleJobOffer> toSimpleJobOffers(List<JobOffer> jobOffers);

  default UUID map(JobOfferId id) {
    return id == null ? null : UUID.fromString(id.getValueAsString());
  }

  @Mapping(source = "salaryRange.min", target = "minSalary")
  @Mapping(source = "salaryRange.max", target = "maxSalary")
  JobOfferResponse toJobOfferResponse(JobOffer jobOffer);
}
