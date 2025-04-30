package com.antharos.joboffer.infrastructure.out.repository.candidate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCandidateRepository extends JpaRepository<CandidateEntity, UUID> {
  List<CandidateEntity> findAll();

  Optional<CandidateEntity> findByPersonalEmail(String personalEmail);

  List<CandidateEntity> findByJobOffer_Id(UUID jobOfferId);
}
