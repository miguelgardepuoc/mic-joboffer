package com.antharos.joboffer.domain.candidate.repository;

import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.domain.candidate.CandidateId;
import java.util.List;
import java.util.Optional;

public interface CandidateRepository {
  List<Candidate> findAll();

  void saveCandidate(Candidate candidate);

  Optional<Candidate> findBy(CandidateId candidateId);

  Optional<Candidate> findByPersonalEmail(String personalEmail);
}
