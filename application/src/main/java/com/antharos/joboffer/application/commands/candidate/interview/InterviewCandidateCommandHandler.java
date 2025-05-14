package com.antharos.joboffer.application.commands.candidate.interview;

import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.domain.candidate.exception.CandidateNotFoundException;
import com.antharos.joboffer.domain.candidate.repository.CandidateRepository;
import com.antharos.joboffer.domain.candidate.valueobject.CandidateId;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InterviewCandidateCommandHandler {

  private final CandidateRepository candidateRepository;

  public void doHandle(final InterviewCandidateCommand command) {

    final CandidateId candidateId = CandidateId.of(command.getCandidateId());

    Candidate candidate =
        this.candidateRepository
            .findBy(candidateId)
            .orElseThrow(
                () -> new CandidateNotFoundException(UUID.fromString(command.getCandidateId())));

    candidate.interview(command.getByUser());
    this.candidateRepository.saveCandidate(candidate);
  }
}
