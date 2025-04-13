package com.antharos.joboffer.infrastructure.apirest.presentationmodel;

import lombok.Getter;

@Getter
public class AddCandidateRequest {
  String id;
  String jobOfferId;
  String personalEmail;
  String cvUrl;
}
