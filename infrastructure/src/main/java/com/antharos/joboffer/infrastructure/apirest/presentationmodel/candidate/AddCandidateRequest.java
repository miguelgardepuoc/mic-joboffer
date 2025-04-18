package com.antharos.joboffer.infrastructure.apirest.presentationmodel.candidate;

import lombok.Getter;

@Getter
public class AddCandidateRequest {
  String id;
  String jobOfferId;
  String personalEmail;
  String cvUrl;
}
