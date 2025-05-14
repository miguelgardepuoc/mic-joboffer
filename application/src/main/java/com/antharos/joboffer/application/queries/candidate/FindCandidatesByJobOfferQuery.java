package com.antharos.joboffer.application.queries.candidate;

import java.util.UUID;
import lombok.Value;

@Value(staticConstructor = "of")
public class FindCandidatesByJobOfferQuery {
  UUID jobOfferId;
}
