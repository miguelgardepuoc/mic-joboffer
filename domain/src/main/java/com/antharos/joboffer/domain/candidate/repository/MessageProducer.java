package com.antharos.joboffer.domain.candidate.repository;

import com.antharos.joboffer.domain.candidate.Candidate;
import java.util.UUID;

public interface MessageProducer {
  void sendMessage(UUID id, final String subject, Candidate candidate);

  void sendUserHiredMessage(Candidate candidate);
}
