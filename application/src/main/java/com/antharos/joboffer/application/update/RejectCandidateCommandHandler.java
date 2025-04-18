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
public class RejectCandidateCommandHandler {

  private final CandidateRepository candidateRepository;

  public void doHandle(final RejectCandidateCommand command) {

    final CandidateId candidateId = CandidateId.of(command.getCandidateId());

    Candidate candidate =
        this.candidateRepository
            .findBy(candidateId)
            .orElseThrow(
                () -> new CandidateNotFoundException(UUID.fromString(command.getCandidateId())));

    candidate.reject(command.getByUser());
    this.candidateRepository.saveCandidate(candidate);
  }
}
