package com.antharos.joboffer.domain.candidate.repository;

import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.domain.candidate.CandidateId;
import java.util.Optional;

public interface CandidateRepository {
  Optional<Candidate> findBy(CandidateId candidateId);
}
