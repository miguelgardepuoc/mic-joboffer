package com.antharos.joboffer.application.queries.candidate;

import lombok.Value;

@Value(staticConstructor = "of")
public class FindCandidateByPersonalEmailQuery {
  String personalEmail;
}
