package com.antharos.joboffer.application.find;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.repository.JobOfferRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Component
@RequiredArgsConstructor
public class FindJobOffersQueryHandler {
  private final JobOfferRepository repository;

  public List<JobOffer> handle(final FindJobOffersQuery query) {
    return this.repository.findAllActive();
  }
}
