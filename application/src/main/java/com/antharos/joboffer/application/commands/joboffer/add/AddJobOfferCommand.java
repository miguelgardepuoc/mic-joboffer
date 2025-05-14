package com.antharos.joboffer.application.commands.joboffer.add;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

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
