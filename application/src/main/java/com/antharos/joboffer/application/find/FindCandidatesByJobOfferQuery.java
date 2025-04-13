package com.antharos.joboffer.application.find;

import lombok.Value;

import java.util.UUID;

@Value(staticConstructor = "of")
public class FindCandidatesByJobOfferQuery {
    UUID jobOfferId;
}
