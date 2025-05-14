package com.antharos.joboffer.application.queries.candidate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.domain.candidate.repository.CandidateRepository;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindCandidatesByJobOfferQueryHandlerUnitTest {

  private CandidateRepository candidateRepository;
  private FindCandidatesByJobOfferQueryHandler handler;

  @BeforeEach
  void setUp() {
    candidateRepository = mock(CandidateRepository.class);
    handler = new FindCandidatesByJobOfferQueryHandler(candidateRepository);
  }

  @Test
  void shouldReturnCandidatesForJobOffer() {
    UUID jobOfferId = UUID.randomUUID();
    List<Candidate> candidates = List.of(mock(Candidate.class), mock(Candidate.class));
    when(candidateRepository.findByJobOfferId(jobOfferId)).thenReturn(candidates);

    FindCandidatesByJobOfferQuery query = FindCandidatesByJobOfferQuery.of(jobOfferId);

    List<Candidate> result = handler.handle(query);

    assertNotNull(result);
    assertEquals(2, result.size());
    verify(candidateRepository).findByJobOfferId(jobOfferId);
  }

  @Test
  void shouldReturnEmptyListWhenNoCandidatesFound() {
    UUID jobOfferId = UUID.randomUUID();
    when(candidateRepository.findByJobOfferId(jobOfferId)).thenReturn(List.of());

    FindCandidatesByJobOfferQuery query = FindCandidatesByJobOfferQuery.of(jobOfferId);

    List<Candidate> result = handler.handle(query);

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }
}
