package com.antharos.joboffer.application.queries.candidate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.domain.candidate.repository.CandidateRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindCandidateByPersonalEmailQueryHandlerUnitTest {

  private CandidateRepository candidateRepository;
  private FindCandidateByPersonalEmailQueryHandler handler;

  @BeforeEach
  void setUp() {
    candidateRepository = mock(CandidateRepository.class);
    handler = new FindCandidateByPersonalEmailQueryHandler(candidateRepository);
  }

  @Test
  void shouldReturnCandidateWhenFound() {
    String personalEmail = "test@example.com";
    Candidate candidate = mock(Candidate.class);
    when(candidateRepository.findByPersonalEmail(personalEmail)).thenReturn(Optional.of(candidate));

    FindCandidateByPersonalEmailQuery query = FindCandidateByPersonalEmailQuery.of(personalEmail);

    Candidate result = handler.handle(query);

    assertNotNull(result);
    assertEquals(candidate, result);
  }

  @Test
  void shouldReturnNullWhenCandidateNotFound() {
    String personalEmail = "test@example.com";
    when(candidateRepository.findByPersonalEmail(personalEmail)).thenReturn(Optional.empty());

    FindCandidateByPersonalEmailQuery query = FindCandidateByPersonalEmailQuery.of(personalEmail);

    Candidate result = handler.handle(query);

    assertNull(result);
  }
}
