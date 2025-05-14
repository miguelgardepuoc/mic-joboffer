package com.antharos.joboffer.application.queries.joboffer;

import java.util.UUID;
import lombok.Value;

@Value(staticConstructor = "of")
public class FindJobOfferQuery {
  UUID jobOfferId;
}
