package com.antharos.joboffer.infrastructure.out.event.model;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CandidatePayload {
  private UUID id;
  private String personalEmail;
  private String cvFilename;
  private String name;
  private String surname;
  private String telephoneNumber;
  private String jobOfferId;
}
