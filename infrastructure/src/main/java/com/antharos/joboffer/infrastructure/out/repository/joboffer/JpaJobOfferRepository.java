package com.antharos.joboffer.infrastructure.out.repository.joboffer;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaJobOfferRepository extends JpaRepository<JobOfferEntity, UUID> {
  List<JobOfferEntity> findAllByIsActiveTrue();
}
