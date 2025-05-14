package com.antharos.joboffer.domain.candidate.repository;

import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.domain.candidate.valueobject.CandidateId;
import com.antharos.joboffer.domain.candidate.valueobject.PersonalEmail;
import com.antharos.joboffer.domain.joboffer.valueobject.JobOfferId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository {
  void saveCandidate(Candidate candidate);

  Optional<Candidate> findBy(CandidateId candidateId);

  Optional<Candidate> findByPersonalEmail(String personalEmail);

  List<Candidate> findByJobOfferId(UUID jobOfferId);

  boolean existsByEmail(JobOfferId jobOfferId, PersonalEmail personalEmail);

  Optional<Candidate> findById(String id);
}
