package com.antharos.joboffer.application.queries.joboffer;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.exception.JobOfferNotFoundException;
import com.antharos.joboffer.domain.joboffer.repository.JobOfferRepository;
import com.antharos.joboffer.domain.joboffer.valueobject.JobOfferId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FindJobOfferQueryHandler {
  private final JobOfferRepository repository;

  public JobOffer handle(FindJobOfferQuery query) {
    return this.repository
        .findById(JobOfferId.of(query.getJobOfferId().toString()))
        .orElseThrow(() -> new JobOfferNotFoundException(query.getJobOfferId().toString()));
  }
}
