package com.antharos.joboffer.infrastructure.apirest.presentationmodel;

import java.math.BigDecimal;
import java.util.UUID;

public record SimpleJobOffer(
    UUID id, UUID jobTitleId, BigDecimal minSalary, BigDecimal maxSalary) {}
