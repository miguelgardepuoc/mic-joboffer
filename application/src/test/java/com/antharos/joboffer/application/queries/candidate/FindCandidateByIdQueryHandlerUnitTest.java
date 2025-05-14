package com.antharos.joboffer.application.queries.candidate;

import static org.mockito.Mockito.*;

import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.domain.candidate.repository.CandidateRepository;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindCandidateByIdQueryHandlerUnitTest {

  private CandidateRepository candidateRepository;
  private FindCandidateByIdQueryHandler handler;

  @BeforeEach
  void setUp() {
    candidateRepository = mock(CandidateRepository.class);
    handler = new FindCandidateByIdQueryHandler(candidateRepository);
  }

  @Test
  void shouldReturnCandidateWhenFound() {
    String candidateId = UUID.randomUUID().toString();
    Candidate candidate = mock(Candidate.class);
    when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));

    FindCandidateByIdQuery query = FindCandidateByIdQuery.of(candidateId);

    Candidate result = handler.handle(query);

    verify(candidateRepository).findById(candidateId);
    assert result != null;
  }

  @Test
  void shouldReturnNullWhenCandidateNotFound() {
    String candidateId = UUID.randomUUID().toString();
    when(candidateRepository.findById(candidateId)).thenReturn(Optional.empty());

    FindCandidateByIdQuery query = FindCandidateByIdQuery.of(candidateId);

    Candidate result = handler.handle(query);

    verify(candidateRepository).findById(candidateId);
    assert result == null;
  }
}
