package com.antharos.joboffer.infrastructure.out.event.model;

import lombok.Getter;

@Getter
public enum EventNames {
  CANDIDATE_APPLIED("CandidateApplied");

  private final String description;

  EventNames(String description) {
    this.description = description;
  }
}
