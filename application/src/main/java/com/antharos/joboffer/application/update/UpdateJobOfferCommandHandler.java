package com.antharos.joboffer.application.update;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.JobOfferId;
import com.antharos.joboffer.domain.joboffer.JobOfferNotFoundException;
import com.antharos.joboffer.domain.joboffer.SalaryRange;
import com.antharos.joboffer.domain.joboffer.repository.JobOfferRepository;
import java.util.UUID;
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
            .orElseThrow(() -> new JobOfferNotFoundException(UUID.fromString(command.getId())));

    jobOffer.update(
        command.getDescription(),
        salaryRange,
        command.getRemote(),
        command.getRequirement(),
        command.getLastModifiedBy());

    this.jobOfferRepository.save(jobOffer);
  }
}
