package com.antharos.joboffer.application.commands.candidate.hire;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class HireCandidateCommand {
  String candidateId;
  String byUser;
}
