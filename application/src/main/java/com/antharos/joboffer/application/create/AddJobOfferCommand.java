package com.antharos.joboffer.application.create;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Value
public class AddJobOfferCommand {
    String id;
    UUID jobTitleId;
    String description;
    BigDecimal minSalary;
    BigDecimal maxSalary;
    short remote;
    String requirement;
    String createdBy;
}
