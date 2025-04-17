package com.antharos.joboffer.infrastructure.apirest.presentationmodel;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class AddJobOfferRequest {
    String id;
    UUID jobTitleId;
    String description;
    BigDecimal minSalary;
    BigDecimal maxSalary;
    short remote;
    String requirement;
}
