package com.antharos.joboffer.domain.candidate.valueobject;

import com.antharos.joboffer.domain.candidate.exception.InvalidCandidateException;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CandidateId {

  String valueAsString;

  public static CandidateId of(String candidateId) {
    try {
      UUID.fromString(candidateId);
    } catch (IllegalArgumentException e) {
      throw new InvalidCandidateException(
          "CandidateId must be a valid UUID. Invalid string value: " + candidateId);
    }
    return new CandidateId(candidateId);
  }

  @Override
  public String toString() {
    return valueAsString;
  }
}
