package com.antharos.joboffer.application.commands.joboffer.update;

import static org.mockito.Mockito.*;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.exception.JobOfferNotFoundException;
import com.antharos.joboffer.domain.joboffer.repository.JobOfferRepository;
import com.antharos.joboffer.domain.joboffer.valueobject.JobOfferId;
import com.antharos.joboffer.domain.joboffer.valueobject.SalaryRange;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateJobOfferCommandHandlerUnitTest {

  private JobOfferRepository jobOfferRepository;
  private UpdateJobOfferCommandHandler handler;

  @BeforeEach
  void setUp() {
    jobOfferRepository = mock(JobOfferRepository.class);
    handler = new UpdateJobOfferCommandHandler(jobOfferRepository);
  }

  @Test
  void shouldUpdateJobOffer() {
    String jobOfferId = UUID.randomUUID().toString();
    String description = "Senior Software Engineer";
    BigDecimal minSalary = BigDecimal.valueOf(60000);
    BigDecimal maxSalary = BigDecimal.valueOf(90000);
    short remote = 0;
    String requirement = "Must have 7 years of experience";
    String lastModifiedBy = "admin";

    JobOffer jobOffer = mock(JobOffer.class);
    when(jobOfferRepository.findById(JobOfferId.of(jobOfferId)))
        .thenReturn(java.util.Optional.of(jobOffer));

    UpdateJobOfferCommand command =
        new UpdateJobOfferCommand(
            jobOfferId, description, minSalary, maxSalary, remote, requirement, lastModifiedBy);

    handler.doHandle(command);

    verify(jobOffer)
        .update(
            description,
            new SalaryRange(minSalary, maxSalary),
            remote,
            requirement,
            lastModifiedBy);
    verify(jobOfferRepository).save(jobOffer);
  }

  @Test
  void shouldThrowWhenJobOfferNotFound() {
    String jobOfferId = UUID.randomUUID().toString();

    when(jobOfferRepository.findById(JobOfferId.of(jobOfferId)))
        .thenReturn(java.util.Optional.empty());

    UpdateJobOfferCommand command =
        new UpdateJobOfferCommand(
            jobOfferId,
            "description",
            BigDecimal.valueOf(60000),
            BigDecimal.valueOf(100000),
            (short) 0,
            "requirement",
            "admin");

    org.junit.jupiter.api.Assertions.assertThrows(
        JobOfferNotFoundException.class, () -> handler.doHandle(command));
  }
}
