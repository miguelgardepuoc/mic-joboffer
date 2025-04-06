package com.antharos.joboffer.domain.joboffer;

import com.antharos.joboffer.domain.globalexceptions.AlreadyExistsException;

public class JobOfferAlreadyExists extends AlreadyExistsException {
  public JobOfferAlreadyExists() {
    super("Job offer already exists");
  }
}
