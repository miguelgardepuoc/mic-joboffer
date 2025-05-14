package com.antharos.joboffer.domain.joboffer.valueobject;

import com.antharos.joboffer.domain.joboffer.exception.InvalidJobOfferException;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class JobOfferId {

  String valueAsString;

  public static JobOfferId of(String jobOfferId) {
    try {
      UUID.fromString(jobOfferId);
    } catch (IllegalArgumentException e) {
      throw new InvalidJobOfferException(
          "JobOfferId must be a valid UUID. Invalid string value: " + jobOfferId);
    }
    return new JobOfferId(jobOfferId);
  }

  @Override
  public String toString() {
    return valueAsString;
  }
}
