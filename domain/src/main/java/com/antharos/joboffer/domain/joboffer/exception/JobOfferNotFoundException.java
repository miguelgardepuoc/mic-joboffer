package com.antharos.joboffer.domain.joboffer.exception;

import com.antharos.joboffer.domain.globalexceptions.NotFoundException;

public class JobOfferNotFoundException extends NotFoundException {
  public JobOfferNotFoundException(String id) {
    super("Job offer not found with ID: " + id);
  }
}
