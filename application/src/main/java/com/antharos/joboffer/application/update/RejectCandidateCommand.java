package com.antharos.joboffer.application.update;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RejectCandidateCommand {
    String candidateId;
    String byUser;
}
