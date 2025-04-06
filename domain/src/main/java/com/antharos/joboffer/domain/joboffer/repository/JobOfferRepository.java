package com.antharos.joboffer.domain.joboffer.repository;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import java.util.List;

public interface JobOfferRepository {

  List<JobOffer> findAllActive();
}
