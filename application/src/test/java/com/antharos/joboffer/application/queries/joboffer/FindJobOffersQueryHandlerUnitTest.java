package com.antharos.joboffer.application.queries.joboffer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.repository.JobOfferRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindJobOffersQueryHandlerUnitTest {

  private JobOfferRepository jobOfferRepository;
  private FindJobOffersQueryHandler handler;

  @BeforeEach
  void setUp() {
    jobOfferRepository = mock(JobOfferRepository.class);
    handler = new FindJobOffersQueryHandler(jobOfferRepository);
  }

  @Test
  void shouldReturnListOfActiveJobOffers() {
    List<JobOffer> jobOffers = List.of(mock(JobOffer.class), mock(JobOffer.class));
    when(jobOfferRepository.findAllActive()).thenReturn(jobOffers);

    FindJobOffersQuery query = FindJobOffersQuery.of();

    List<JobOffer> result = handler.handle(query);

    assertNotNull(result);
    assertEquals(2, result.size());
    verify(jobOfferRepository).findAllActive();
  }

  @Test
  void shouldReturnEmptyListWhenNoActiveJobOffers() {
    when(jobOfferRepository.findAllActive()).thenReturn(List.of());

    FindJobOffersQuery query = FindJobOffersQuery.of();

    List<JobOffer> result = handler.handle(query);

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }
}
