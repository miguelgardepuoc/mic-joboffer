package com.antharos.joboffer.infrastructure.repository.joboffer;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.JobOfferId;
import com.antharos.joboffer.domain.joboffer.repository.JobOfferRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JobOfferRepositoryAdapter implements JobOfferRepository {

  private final JpaJobOfferRepository jpaRepository;
  private final JobOfferMapper mapper;

  @Override
  public List<JobOffer> findAllActive() {
    return this.jpaRepository.findAllByIsActiveTrue().stream().map(this.mapper::toDomain).toList();
  }

  @Override
  public Optional<JobOffer> findById(final JobOfferId jobOfferId) {
    return this.jpaRepository
        .findById(UUID.fromString(jobOfferId.getValueAsString()))
        .map(this.mapper::toDomain);
  }

  @Override
  public void save(JobOffer jobOffer) {
    this.jpaRepository.save(this.mapper.toEntity(jobOffer));
  }
}
