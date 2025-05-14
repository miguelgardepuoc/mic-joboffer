package com.antharos.joboffer.application.commands.candidate.updatefullname;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdateCandidateFullNameCommand {
  String candidateId;
  String name;
  String surname;
  String byUser;
}
