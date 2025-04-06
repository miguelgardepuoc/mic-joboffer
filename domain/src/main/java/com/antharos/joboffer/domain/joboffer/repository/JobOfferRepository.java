package com.antharos.joboffer.domain.joboffer.repository;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.JobOfferId;
import java.util.Optional;

public interface JobOfferRepository {
  Optional<JobOffer> findBy(JobOfferId jobOfferId);

  void save(JobOffer jobOffer);
}
