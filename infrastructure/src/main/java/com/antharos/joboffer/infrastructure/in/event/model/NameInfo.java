package com.antharos.joboffer.infrastructure.in.event.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NameInfo {
  private String candidateId;
  private String name;
  private String surname;
}
