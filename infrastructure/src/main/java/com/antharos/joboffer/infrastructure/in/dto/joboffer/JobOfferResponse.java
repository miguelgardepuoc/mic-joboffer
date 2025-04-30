package com.antharos.joboffer.infrastructure.in.dto.joboffer;

import java.math.BigDecimal;
import java.util.UUID;

public record JobOfferResponse(
    UUID id,
    UUID jobTitleId,
    BigDecimal minSalary,
    BigDecimal maxSalary,
    Float remote,
    String description,
    String requirement) {}
