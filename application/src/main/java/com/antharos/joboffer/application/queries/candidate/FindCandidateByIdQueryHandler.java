package com.antharos.joboffer.application.queries.candidate;

import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.domain.candidate.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FindCandidateByIdQueryHandler {
  private final CandidateRepository repository;

  public Candidate handle(FindCandidateByIdQuery query) {
    return this.repository.findById(query.getId()).orElse(null);
  }
}
