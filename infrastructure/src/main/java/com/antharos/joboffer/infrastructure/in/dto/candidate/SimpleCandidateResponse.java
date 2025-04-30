package com.antharos.joboffer.infrastructure.in.dto.candidate;

import com.antharos.joboffer.domain.candidate.CandidateStatus;
import java.util.UUID;

public record SimpleCandidateResponse(
    UUID id,
    CandidateStatus status,
    String personalEmail,
    String cvFilename,
    String name,
    String surname,
    String phoneNumber) {}
