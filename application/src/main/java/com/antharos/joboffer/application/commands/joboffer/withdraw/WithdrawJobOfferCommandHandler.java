package com.antharos.joboffer.application.commands.joboffer.withdraw;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.exception.JobOfferNotFoundException;
import com.antharos.joboffer.domain.joboffer.repository.JobOfferRepository;
import com.antharos.joboffer.domain.joboffer.valueobject.JobOfferId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WithdrawJobOfferCommandHandler {

  private final JobOfferRepository jobOfferRepository;

  public void doHandle(final WithdrawJobOfferCommand command) {

    final JobOfferId jobOfferId = JobOfferId.of(command.getId());

    JobOffer jobOffer =
        this.jobOfferRepository
            .findById(jobOfferId)
            .orElseThrow(() -> new JobOfferNotFoundException(command.getId()));

    jobOffer.withdraw(command.getLastModifiedBy());

    this.jobOfferRepository.save(jobOffer);
  }
}
