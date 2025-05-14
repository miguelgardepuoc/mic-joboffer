package com.antharos.joboffer.domain.candidate.exception;

import com.antharos.joboffer.domain.globalexceptions.NotFoundException;
import java.util.UUID;

public class CandidateNotFoundException extends NotFoundException {
  public CandidateNotFoundException(UUID candidateId) {
    super("Candidate not found with ID: " + candidateId);
  }
}
