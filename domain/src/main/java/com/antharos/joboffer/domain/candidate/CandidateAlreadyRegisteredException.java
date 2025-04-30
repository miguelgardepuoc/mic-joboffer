package com.antharos.joboffer.domain.candidate;

import com.antharos.joboffer.domain.globalexceptions.ConflictException;

public class CandidateAlreadyRegisteredException extends ConflictException {
  public CandidateAlreadyRegisteredException() {
    super();
  }
}
