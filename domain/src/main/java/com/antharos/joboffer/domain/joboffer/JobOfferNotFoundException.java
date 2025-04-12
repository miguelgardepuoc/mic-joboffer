package com.antharos.joboffer.domain.joboffer;

import com.antharos.joboffer.domain.globalexceptions.NotFoundException;

import java.util.UUID;

public class JobOfferNotFoundException extends NotFoundException {
    public JobOfferNotFoundException(UUID id) {
        super("Job offer not found with ID: " + id);
    }
}
