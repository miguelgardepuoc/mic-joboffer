package com.antharos.joboffer.domain.candidate;

import com.antharos.joboffer.domain.globalexceptions.AlreadyExistsException;

public class CandidateAlreadyExists extends AlreadyExistsException {
  public CandidateAlreadyExists() {
    super("Candidate already exists");
  }
}
