package com.antharos.joboffer.application.create;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AddCandidateCommand {
  String id;
  String jobOfferId;
  String personalEmail;
  String cvUrl;
  String createdBy;
}
