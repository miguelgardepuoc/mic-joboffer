package com.antharos.joboffer.application.commands.candidate.add;

import com.antharos.joboffer.domain.candidate.*;
import com.antharos.joboffer.domain.candidate.exception.CandidateAlreadyExistsException;
import com.antharos.joboffer.domain.candidate.exception.CandidateAlreadyRegisteredException;
import com.antharos.joboffer.domain.candidate.repository.CandidateRepository;
import com.antharos.joboffer.domain.candidate.repository.MessageProducer;
import com.antharos.joboffer.domain.candidate.valueobject.CandidateId;
import com.antharos.joboffer.domain.candidate.valueobject.PersonalEmail;
import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.exception.JobOfferNotFoundException;
import com.antharos.joboffer.domain.joboffer.repository.JobOfferRepository;
import com.antharos.joboffer.domain.joboffer.valueobject.JobOfferId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddCandidateCommandHandler {

  private final CandidateRepository candidateRepository;
  private final JobOfferRepository jobOfferRepository;
  private final MessageProducer messageProducer;

  public void doHandle(final AddCandidateCommand command) {

    final JobOfferId jobOfferId = JobOfferId.of(command.getJobOfferId());

    JobOffer jobOffer =
        this.jobOfferRepository
            .findById(jobOfferId)
            .orElseThrow(() -> new JobOfferNotFoundException(command.getJobOfferId()));

    final CandidateId candidateId = CandidateId.of(command.getId());

    this.candidateRepository
        .findBy(candidateId)
        .ifPresent(
            existing -> {
              throw new CandidateAlreadyExistsException();
            });

    PersonalEmail personalEmail = new PersonalEmail(command.getPersonalEmail());

    if (this.candidateRepository.existsByEmail(jobOfferId, personalEmail)) {
      throw new CandidateAlreadyRegisteredException();
    }

    Candidate newCandidate =
        Candidate.create(
            candidateId,
            personalEmail,
            command.getCvFilename(),
            jobOffer.getId(),
            command.getCreatedBy());

    this.candidateRepository.saveCandidate(newCandidate);
    this.messageProducer.sendCandidateAppliedEvent(newCandidate);
  }
}
