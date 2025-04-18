package com.antharos.joboffer.infrastructure.apirest.presentationmodel.candidate;

import com.antharos.joboffer.domain.candidate.CandidateStatus;
import java.util.UUID;

public record SimpleCandidateResponse(
    UUID id,
    CandidateStatus status,
    String personalEmail,
    String cvUrl,
    String name,
    String surname,
    String phoneNumber) {}
