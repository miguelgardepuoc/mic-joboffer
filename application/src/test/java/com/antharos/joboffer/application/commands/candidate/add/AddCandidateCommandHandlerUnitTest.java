package com.antharos.joboffer.application.commands.candidate.add;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.antharos.joboffer.domain.candidate.*;
import com.antharos.joboffer.domain.candidate.exception.CandidateAlreadyExistsException;
import com.antharos.joboffer.domain.candidate.exception.CandidateAlreadyRegisteredException;
import com.antharos.joboffer.domain.candidate.repository.CandidateRepository;
import com.antharos.joboffer.domain.candidate.repository.MessageProducer;
import com.antharos.joboffer.domain.candidate.valueobject.CandidateId;
import com.antharos.joboffer.domain.candidate.valueobject.PersonalEmail;
import com.antharos.joboffer.domain.joboffer.JobOffer;
import com.antharos.joboffer.domain.joboffer.exception.JobOfferNotFoundException;
import com.antharos.joboffer.domain.joboffer.repository.JobOfferRepository;
import com.antharos.joboffer.domain.joboffer.valueobject.JobOfferId;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddCandidateCommandHandlerUnitTest {

  private CandidateRepository candidateRepository;
  private JobOfferRepository jobOfferRepository;
  private MessageProducer messageProducer;

  private AddCandidateCommandHandler handler;

  @BeforeEach
  void setUp() {
    candidateRepository = mock(CandidateRepository.class);
    jobOfferRepository = mock(JobOfferRepository.class);
    messageProducer = mock(MessageProducer.class);
    handler =
        new AddCandidateCommandHandler(candidateRepository, jobOfferRepository, messageProducer);
  }

  @Test
  void shouldAddCandidateSuccessfully() {
    String candidateId = UUID.randomUUID().toString();
    String jobOfferId = UUID.randomUUID().toString();
    String email = "test@example.com";

    JobOffer mockJobOffer = mock(JobOffer.class);
    when(mockJobOffer.getId()).thenReturn(JobOfferId.of(jobOfferId));

    AddCandidateCommand command =
        AddCandidateCommand.builder()
            .id(candidateId)
            .jobOfferId(jobOfferId)
            .personalEmail(email)
            .cvFilename("cv.pdf")
            .createdBy("admin")
            .build();

    when(jobOfferRepository.findById(JobOfferId.of(jobOfferId)))
        .thenReturn(Optional.of(mockJobOffer));
    when(candidateRepository.findBy(CandidateId.of(candidateId))).thenReturn(Optional.empty());
    when(candidateRepository.existsByEmail(JobOfferId.of(jobOfferId), new PersonalEmail(email)))
        .thenReturn(false);

    handler.doHandle(command);

    verify(candidateRepository).saveCandidate(any(Candidate.class));
    verify(messageProducer).sendCandidateAppliedEvent(any(Candidate.class));
  }

  @Test
  void shouldThrowWhenJobOfferNotFound() {
    String candidateId = UUID.randomUUID().toString();
    String jobOfferId = UUID.randomUUID().toString();
    AddCandidateCommand command =
        AddCandidateCommand.builder()
            .id(candidateId)
            .jobOfferId(jobOfferId)
            .personalEmail("test@example.com")
            .cvFilename("cv.pdf")
            .createdBy("admin")
            .build();

    when(jobOfferRepository.findById(JobOfferId.of(jobOfferId))).thenReturn(Optional.empty());

    assertThrows(JobOfferNotFoundException.class, () -> handler.doHandle(command));
  }

  @Test
  void shouldThrowWhenCandidateAlreadyExists() {
    String candidateId = UUID.randomUUID().toString();
    String jobOfferId = UUID.randomUUID().toString();
    String email = "test@example.com";

    when(jobOfferRepository.findById(JobOfferId.of(jobOfferId)))
        .thenReturn(Optional.of(mock(JobOffer.class)));
    when(candidateRepository.findBy(CandidateId.of(candidateId)))
        .thenReturn(Optional.of(mock(Candidate.class)));

    AddCandidateCommand command =
        AddCandidateCommand.builder()
            .id(candidateId)
            .jobOfferId(jobOfferId)
            .personalEmail(email)
            .cvFilename("cv.pdf")
            .createdBy("admin")
            .build();

    assertThrows(CandidateAlreadyExistsException.class, () -> handler.doHandle(command));
  }

  @Test
  void shouldThrowWhenEmailAlreadyRegistered() {
    String candidateId = UUID.randomUUID().toString();
    String jobOfferId = UUID.randomUUID().toString();
    String email = "test@example.com";

    when(jobOfferRepository.findById(JobOfferId.of(jobOfferId)))
        .thenReturn(Optional.of(mock(JobOffer.class)));
    when(candidateRepository.findBy(CandidateId.of(candidateId))).thenReturn(Optional.empty());
    when(candidateRepository.existsByEmail(JobOfferId.of(jobOfferId), new PersonalEmail(email)))
        .thenReturn(true);

    AddCandidateCommand command =
        AddCandidateCommand.builder()
            .id(candidateId)
            .jobOfferId(jobOfferId)
            .personalEmail(email)
            .cvFilename("cv.pdf")
            .createdBy("admin")
            .build();

    assertThrows(CandidateAlreadyRegisteredException.class, () -> handler.doHandle(command));
  }
}
