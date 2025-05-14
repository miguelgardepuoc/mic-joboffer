package com.antharos.joboffer.domain.candidate.exception;

import com.antharos.joboffer.domain.globalexceptions.ConflictException;

public class CandidateAlreadyRegisteredException extends ConflictException {
  public CandidateAlreadyRegisteredException() {
    super();
  }
}
