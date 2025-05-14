package com.antharos.joboffer.application.commands.candidate.updatefullname;

import com.antharos.joboffer.domain.candidate.*;
import com.antharos.joboffer.domain.candidate.exception.CandidateNotFoundException;
import com.antharos.joboffer.domain.candidate.repository.CandidateRepository;
import com.antharos.joboffer.domain.candidate.valueobject.CandidateId;
import com.antharos.joboffer.domain.candidate.valueobject.Name;
import com.antharos.joboffer.domain.candidate.valueobject.Surname;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateCandidateFullNameCommandHandler {

  private final CandidateRepository candidateRepository;

  public void doHandle(final UpdateCandidateFullNameCommand command) {

    final CandidateId candidateId = CandidateId.of(command.getCandidateId());

    Candidate candidate =
        this.candidateRepository
            .findBy(candidateId)
            .orElseThrow(
                () -> new CandidateNotFoundException(UUID.fromString(command.getCandidateId())));

    candidate.fullname(
        new Name(command.getName()), new Surname(command.getSurname()), command.getByUser());
    this.candidateRepository.saveCandidate(candidate);
  }
}
