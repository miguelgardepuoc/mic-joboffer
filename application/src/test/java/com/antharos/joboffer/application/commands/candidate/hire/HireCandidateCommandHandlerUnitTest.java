package com.antharos.joboffer.application.commands.candidate.hire;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.domain.candidate.exception.CandidateNotFoundException;
import com.antharos.joboffer.domain.candidate.repository.CandidateRepository;
import com.antharos.joboffer.domain.candidate.valueobject.CandidateId;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HireCandidateCommandHandlerUnitTest {

  private CandidateRepository candidateRepository;
  private HireCandidateCommandHandler handler;

  @BeforeEach
  void setUp() {
    candidateRepository = mock(CandidateRepository.class);
    handler = new HireCandidateCommandHandler(candidateRepository);
  }

  @Test
  void shouldHireCandidateSuccessfully() {
    String candidateUuid = UUID.randomUUID().toString();
    String byUser = "hr_user";

    CandidateId candidateId = CandidateId.of(candidateUuid);
    Candidate candidate = mock(Candidate.class);

    when(candidateRepository.findBy(candidateId)).thenReturn(Optional.of(candidate));

    HireCandidateCommand command =
        HireCandidateCommand.builder().candidateId(candidateUuid).byUser(byUser).build();

    handler.doHandle(command);

    verify(candidate).hire(byUser);
    verify(candidateRepository).saveCandidate(candidate);
  }

  @Test
  void shouldThrowWhenCandidateNotFound() {
    String candidateUuid = UUID.randomUUID().toString();
    CandidateId candidateId = CandidateId.of(candidateUuid);

    when(candidateRepository.findBy(candidateId)).thenReturn(Optional.empty());

    HireCandidateCommand command =
        HireCandidateCommand.builder().candidateId(candidateUuid).byUser("admin").build();

    assertThrows(CandidateNotFoundException.class, () -> handler.doHandle(command));
  }
}
