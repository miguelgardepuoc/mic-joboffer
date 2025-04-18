package com.antharos.joboffer.application.find;

import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.domain.candidate.repository.CandidateRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FindCandidatesByJobOfferQueryHandler {
  private final CandidateRepository repository;

  public List<Candidate> handle(FindCandidatesByJobOfferQuery query) {
    return this.repository.findByJobOfferId(query.getJobOfferId());
  }
}
