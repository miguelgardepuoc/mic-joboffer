package com.antharos.joboffer.application.commands.candidate.add;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AddCandidateCommand {
  String id;
  String jobOfferId;
  String personalEmail;
  String cvFilename;
  String createdBy;
}
