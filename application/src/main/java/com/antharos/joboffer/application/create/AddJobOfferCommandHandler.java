package com.antharos.joboffer.application.create;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.JobOfferId;
import com.antharos.joboffer.domain.joboffer.SalaryRange;
import com.antharos.joboffer.domain.joboffer.repository.JobOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddJobOfferCommandHandler {

    private final JobOfferRepository jobOfferRepository;

    public void doHandle(final AddJobOfferCommand command) {

        final JobOfferId jobOfferId = JobOfferId.of(command.getId());
        final SalaryRange salaryRange = new SalaryRange(command.getMinSalary(), command.getMaxSalary());

        final JobOffer jobOffer = JobOffer.create(jobOfferId, command.getJobTitleId(), command.getDescription(), salaryRange, command.getRemote(), command.getRequirement(), command.getCreatedBy());

        this.jobOfferRepository.save(jobOffer);
    }
}
