package com.antharos.joboffer.application.update;

import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.domain.candidate.CandidateId;
import com.antharos.joboffer.domain.candidate.CandidateNotFoundException;
import com.antharos.joboffer.domain.candidate.repository.CandidateRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HireCandidateCommandHandler {

  private final CandidateRepository candidateRepository;

  public void doHandle(final HireCandidateCommand command) {

    final CandidateId candidateId = CandidateId.of(command.getCandidateId());

    Candidate candidate =
        this.candidateRepository
            .findBy(candidateId)
            .orElseThrow(
                () -> new CandidateNotFoundException(UUID.fromString(command.getCandidateId())));

    candidate.hire(command.getByUser());
    this.candidateRepository.saveCandidate(candidate);
  }
}
