package com.antharos.joboffer.infrastructure.apirest.presentationmodel.joboffer;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;

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
