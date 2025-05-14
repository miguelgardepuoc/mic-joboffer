package com.antharos.joboffer.application.queries.joboffer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.exception.JobOfferNotFoundException;
import com.antharos.joboffer.domain.joboffer.repository.JobOfferRepository;
import com.antharos.joboffer.domain.joboffer.valueobject.JobOfferId;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindJobOfferQueryHandlerUnitTest {

  private JobOfferRepository jobOfferRepository;
  private FindJobOfferQueryHandler handler;

  @BeforeEach
  void setUp() {
    jobOfferRepository = mock(JobOfferRepository.class);
    handler = new FindJobOfferQueryHandler(jobOfferRepository);
  }

  @Test
  void shouldReturnJobOfferWhenFound() {
    UUID jobOfferId = UUID.randomUUID();
    JobOffer jobOffer = mock(JobOffer.class);
    when(jobOfferRepository.findById(JobOfferId.of(jobOfferId.toString())))
        .thenReturn(Optional.of(jobOffer));

    FindJobOfferQuery query = FindJobOfferQuery.of(jobOfferId);

    JobOffer result = handler.handle(query);

    assertNotNull(result);
    assertEquals(jobOffer, result);
  }

  @Test
  void shouldThrowJobOfferNotFoundExceptionWhenNotFound() {
    UUID jobOfferId = UUID.randomUUID();
    when(jobOfferRepository.findById(JobOfferId.of(jobOfferId.toString())))
        .thenReturn(Optional.empty());

    FindJobOfferQuery query = FindJobOfferQuery.of(jobOfferId);

    JobOfferNotFoundException exception =
        assertThrows(JobOfferNotFoundException.class, () -> handler.handle(query));
    assertEquals("Job offer not found with ID: " + jobOfferId, exception.getMessage());
  }
}
