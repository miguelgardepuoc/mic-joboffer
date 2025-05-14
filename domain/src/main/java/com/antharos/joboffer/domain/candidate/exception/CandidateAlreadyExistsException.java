package com.antharos.joboffer.domain.candidate.exception;

import com.antharos.joboffer.domain.globalexceptions.AlreadyExistsException;

public class CandidateAlreadyExistsException extends AlreadyExistsException {
  public CandidateAlreadyExistsException() {
    super("Candidate already exists");
  }
}
