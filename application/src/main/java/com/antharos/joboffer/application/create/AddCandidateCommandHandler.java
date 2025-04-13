package com.antharos.joboffer.application.create;

import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.domain.candidate.CandidateAlreadyExists;
import com.antharos.joboffer.domain.candidate.CandidateId;
import com.antharos.joboffer.domain.candidate.PersonalEmail;
import com.antharos.joboffer.domain.candidate.repository.CandidateRepository;
import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.JobOfferId;
import com.antharos.joboffer.domain.joboffer.JobOfferNotFoundException;
import com.antharos.joboffer.domain.joboffer.repository.JobOfferRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddCandidateCommandHandler {

  private final CandidateRepository candidateRepository;
  private final JobOfferRepository jobOfferRepository;

  public void doHandle(final AddCandidateCommand command) {

    final JobOfferId jobOfferId = JobOfferId.of(command.getJobOfferId());

    JobOffer jobOffer =
        this.jobOfferRepository
            .findById(jobOfferId)
            .orElseThrow(
                () -> new JobOfferNotFoundException(UUID.fromString(command.getJobOfferId())));

    final CandidateId candidateId = CandidateId.of(command.getId());

    this.candidateRepository
        .findBy(candidateId)
        .ifPresent(
            existing -> {
              throw new CandidateAlreadyExists();
            });

    PersonalEmail personalEmail = new PersonalEmail(command.getPersonalEmail());

    Candidate newCandidate =
        Candidate.create(
            candidateId,
            personalEmail,
            command.getCvUrl(),
            jobOffer.getId(),
            command.getCreatedBy());

    this.candidateRepository.saveCandidate(newCandidate);
  }
}
