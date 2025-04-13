package com.antharos.joboffer.application.find;

import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.domain.candidate.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FindCandidateByPersonalEmailQueryHandler {
  private final CandidateRepository repository;

  public Candidate handle(FindCandidateByPersonalEmailQuery query) {
    return this.repository
        .findByPersonalEmail(query.getPersonalEmail())
        .orElse(null);
  }
}
