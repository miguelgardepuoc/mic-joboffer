package com.antharos.joboffer.domain.candidate.repository;

import com.antharos.joboffer.domain.candidate.Candidate;

public interface MessageProducer {
  void sendCandidateAppliedEvent(Candidate candidate);
}
