package com.antharos.joboffer.application.commands.candidate.updatefullname;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.domain.candidate.exception.CandidateNotFoundException;
import com.antharos.joboffer.domain.candidate.repository.CandidateRepository;
import com.antharos.joboffer.domain.candidate.valueobject.CandidateId;
import com.antharos.joboffer.domain.candidate.valueobject.Name;
import com.antharos.joboffer.domain.candidate.valueobject.Surname;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateCandidateFullNameCommandHandlerUnitTest {

  private CandidateRepository candidateRepository;
  private UpdateCandidateFullNameCommandHandler handler;

  @BeforeEach
  void setUp() {
    candidateRepository = mock(CandidateRepository.class);
    handler = new UpdateCandidateFullNameCommandHandler(candidateRepository);
  }

  @Test
  void shouldUpdateFullNameSuccessfully() {
    String candidateUuid = UUID.randomUUID().toString();
    String name = "Alice";
    String surname = "Johnson";
    String byUser = "admin_user";

    CandidateId candidateId = CandidateId.of(candidateUuid);
    Candidate candidate = mock(Candidate.class);

    when(candidateRepository.findBy(candidateId)).thenReturn(Optional.of(candidate));

    UpdateCandidateFullNameCommand command =
        UpdateCandidateFullNameCommand.builder()
            .candidateId(candidateUuid)
            .name(name)
            .surname(surname)
            .byUser(byUser)
            .build();

    handler.doHandle(command);

    verify(candidate).fullname(new Name(name), new Surname(surname), byUser);
    verify(candidateRepository).saveCandidate(candidate);
  }

  @Test
  void shouldThrowWhenCandidateNotFound() {
    String candidateUuid = UUID.randomUUID().toString();
    CandidateId candidateId = CandidateId.of(candidateUuid);

    when(candidateRepository.findBy(candidateId)).thenReturn(Optional.empty());

    UpdateCandidateFullNameCommand command =
        UpdateCandidateFullNameCommand.builder()
            .candidateId(candidateUuid)
            .name("John")
            .surname("Smith")
            .byUser("admin_user")
            .build();

    assertThrows(CandidateNotFoundException.class, () -> handler.doHandle(command));
  }
}
