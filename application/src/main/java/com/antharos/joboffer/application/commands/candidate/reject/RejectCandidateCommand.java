package com.antharos.joboffer.application.commands.candidate.reject;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RejectCandidateCommand {
  String candidateId;
  String byUser;
}
