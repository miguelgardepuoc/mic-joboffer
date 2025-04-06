package com.antharos.joboffer.infrastructure.repository.joboffer;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.repository.JobOfferRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JobOfferRepositoryAdapter implements JobOfferRepository {

  private final JpaJobOfferRepository jpaJobOfferRepository;
  private final JobOfferMapper jobOfferMapper;

  @Override
  public List<JobOffer> findAllActive() {
    return jpaJobOfferRepository.findAllByIsActiveTrue().stream()
        .map(jobOfferMapper::toDomain)
        .toList();
  }
}
