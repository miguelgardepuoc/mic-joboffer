package com.antharos.joboffer.application.commands.joboffer.withdraw;

import static org.mockito.Mockito.*;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.exception.JobOfferNotFoundException;
import com.antharos.joboffer.domain.joboffer.repository.JobOfferRepository;
import com.antharos.joboffer.domain.joboffer.valueobject.JobOfferId;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WithdrawJobOfferCommandHandlerUnitTest {

  private JobOfferRepository jobOfferRepository;
  private WithdrawJobOfferCommandHandler handler;

  @BeforeEach
  void setUp() {
    jobOfferRepository = mock(JobOfferRepository.class);
    handler = new WithdrawJobOfferCommandHandler(jobOfferRepository);
  }

  @Test
  void shouldWithdrawJobOffer() {
    String jobOfferId = UUID.randomUUID().toString();
    String lastModifiedBy = "admin";

    JobOffer jobOffer = mock(JobOffer.class);
    when(jobOfferRepository.findById(JobOfferId.of(jobOfferId)))
        .thenReturn(java.util.Optional.of(jobOffer));

    WithdrawJobOfferCommand command = new WithdrawJobOfferCommand(jobOfferId, lastModifiedBy);

    handler.doHandle(command);

    verify(jobOffer).withdraw(lastModifiedBy);
    verify(jobOfferRepository).save(jobOffer);
  }

  @Test
  void shouldThrowWhenJobOfferNotFound() {
    String jobOfferId = UUID.randomUUID().toString();

    when(jobOfferRepository.findById(JobOfferId.of(jobOfferId)))
        .thenReturn(java.util.Optional.empty());

    WithdrawJobOfferCommand command = new WithdrawJobOfferCommand(jobOfferId, "admin");

    org.junit.jupiter.api.Assertions.assertThrows(
        JobOfferNotFoundException.class, () -> handler.doHandle(command));
  }
}
