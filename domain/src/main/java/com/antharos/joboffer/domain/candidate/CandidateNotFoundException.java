package com.antharos.joboffer.domain.candidate;

import com.antharos.joboffer.domain.globalexceptions.NotFoundException;

public class CandidateNotFoundException extends NotFoundException {
  public CandidateNotFoundException(String message) {
    super(message);
  }
}
