package com.antharos.joboffer.application.delete;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class WithdrawJobOfferCommand {
  String id;
  String lastModifiedBy;
}
