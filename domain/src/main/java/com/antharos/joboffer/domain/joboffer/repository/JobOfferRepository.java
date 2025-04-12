package com.antharos.joboffer.domain.joboffer.repository;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import java.util.List;
import java.util.UUID;

public interface JobOfferRepository {

  List<JobOffer> findAllActive();
  JobOffer findById(UUID id);
}
