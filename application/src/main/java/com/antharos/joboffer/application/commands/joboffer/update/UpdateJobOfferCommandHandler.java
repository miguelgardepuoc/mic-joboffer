package com.antharos.joboffer.application.commands.joboffer.update;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.exception.JobOfferNotFoundException;
import com.antharos.joboffer.domain.joboffer.repository.JobOfferRepository;
import com.antharos.joboffer.domain.joboffer.valueobject.JobOfferId;
import com.antharos.joboffer.domain.joboffer.valueobject.SalaryRange;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateJobOfferCommandHandler {

  private final JobOfferRepository jobOfferRepository;

  public void doHandle(final UpdateJobOfferCommand command) {

    final JobOfferId jobOfferId = JobOfferId.of(command.getId());
    final SalaryRange salaryRange = new SalaryRange(command.getMinSalary(), command.getMaxSalary());

    JobOffer jobOffer =
        this.jobOfferRepository
            .findById(jobOfferId)
            .orElseThrow(() -> new JobOfferNotFoundException(command.getId()));

    jobOffer.update(
        command.getDescription(),
        salaryRange,
        command.getRemote(),
        command.getRequirement(),
        command.getLastModifiedBy());

    this.jobOfferRepository.save(jobOffer);
  }
}
