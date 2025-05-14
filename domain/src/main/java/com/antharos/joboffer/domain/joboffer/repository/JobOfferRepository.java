package com.antharos.joboffer.domain.joboffer.repository;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.valueobject.JobOfferId;
import java.util.List;
import java.util.Optional;

public interface JobOfferRepository {

  List<JobOffer> findAllActive();

  Optional<JobOffer> findById(JobOfferId jobOfferId);

  void save(JobOffer jobOffer);
}
