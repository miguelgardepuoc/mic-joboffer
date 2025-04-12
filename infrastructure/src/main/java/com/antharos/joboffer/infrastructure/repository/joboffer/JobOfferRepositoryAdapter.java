package com.antharos.joboffer.infrastructure.repository.joboffer;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.repository.JobOfferRepository;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JobOfferRepositoryAdapter implements JobOfferRepository {

  private final JpaJobOfferRepository jpaJobOfferRepository;
  private final JobOfferMapper jobOfferMapper;

  @Override
  public List<JobOffer> findAllActive() {
    return this.jpaJobOfferRepository.findAllByIsActiveTrue().stream()
        .map(this.jobOfferMapper::toDomain)
        .toList();
  }

  @Override
  public JobOffer findById(final UUID id) {
    return this.jpaJobOfferRepository.findById(id)
            .map(this.jobOfferMapper::toDomain)
            .orElse(null);
  }

}
