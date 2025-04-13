package com.antharos.joboffer.infrastructure.apirest.presentationmodel;

import com.antharos.joboffer.domain.candidate.CandidateStatus;
import java.util.UUID;

public record CandidateResponse(
    UUID id,
    UUID jobOfferId,
    CandidateStatus status,
    String personalEmail,
    String cvUrl,
    String name,
    String surname,
    String phoneNumber) {}
