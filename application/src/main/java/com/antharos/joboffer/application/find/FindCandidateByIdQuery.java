package com.antharos.joboffer.application.find;

import lombok.Value;

@Value(staticConstructor = "of")
public class FindCandidateByIdQuery {
  String id;
}
