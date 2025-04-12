package com.antharos.joboffer.application.find;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.repository.JobOfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FindJobOfferQueryHandler {
  private final JobOfferRepository repository;

  public JobOffer handle(FindJobOfferQuery query) {
    return this.repository.findById(query.getJobOfferId());
  }
}
