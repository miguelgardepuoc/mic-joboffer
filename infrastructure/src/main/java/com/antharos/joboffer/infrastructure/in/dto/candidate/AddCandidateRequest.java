package com.antharos.joboffer.infrastructure.in.dto.candidate;

import lombok.Getter;

@Getter
public class AddCandidateRequest {
  String id;
  String jobOfferId;
  String personalEmail;
  String cvFilename;
}
