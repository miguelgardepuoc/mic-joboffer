package com.antharos.joboffer.application.commands.joboffer.add;

import static org.mockito.Mockito.*;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.repository.JobOfferRepository;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddJobOfferCommandHandlerUnitTest {

  private JobOfferRepository jobOfferRepository;
  private AddJobOfferCommandHandler handler;

  @BeforeEach
  void setUp() {
    jobOfferRepository = mock(JobOfferRepository.class);
    handler = new AddJobOfferCommandHandler(jobOfferRepository);
  }

  @Test
  void shouldCreateAndSaveJobOffer() {
    String jobOfferId = UUID.randomUUID().toString();
    UUID jobTitleId = UUID.randomUUID();
    String description = "Software Engineer";
    BigDecimal minSalary = BigDecimal.valueOf(60000);
    BigDecimal maxSalary = BigDecimal.valueOf(90000);
    short remote = 1;
    String requirement = "Must have 5 years of experience";
    String createdBy = "admin";

    AddJobOfferCommand command =
        new AddJobOfferCommand(
            jobOfferId,
            jobTitleId,
            description,
            minSalary,
            maxSalary,
            remote,
            requirement,
            createdBy);

    handler.doHandle(command);

    verify(jobOfferRepository).save(any(JobOffer.class));
  }
}
