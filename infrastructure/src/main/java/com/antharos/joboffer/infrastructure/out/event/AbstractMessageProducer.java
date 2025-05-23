package com.antharos.joboffer.infrastructure.out.event;

import static com.antharos.joboffer.infrastructure.out.event.model.EventNames.CANDIDATE_APPLIED;

import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.domain.candidate.repository.MessageProducer;
import com.antharos.joboffer.infrastructure.out.event.model.BaseEvent;
import com.antharos.joboffer.infrastructure.out.event.model.CandidatePayload;
import com.antharos.joboffer.infrastructure.out.event.model.CandidatePayloadMapper;
import java.time.Instant;
import java.util.UUID;

public abstract class AbstractMessageProducer implements MessageProducer {

  private static final String CANDIDATE_AGGREGATE = "Candidate";
  protected final CandidatePayloadMapper mapper;

  public AbstractMessageProducer(CandidatePayloadMapper mapper) {
    this.mapper = mapper;
  }

  protected BaseEvent<CandidatePayload> buildCandidateAppliedEvent(Candidate candidate) {
    return new BaseEvent<>(
        UUID.randomUUID().toString(),
        Instant.now(),
        CANDIDATE_APPLIED.getDescription(),
        candidate.getId().getValueAsString(),
        CANDIDATE_AGGREGATE,
        candidate.getCreatedBy(),
        1,
        this.mapper.toPayload(candidate));
  }

  @Override
  public void sendCandidateAppliedEvent(Candidate candidate) {
    sendMessage(buildCandidateAppliedEvent(candidate));
  }

  public abstract void sendMessage(BaseEvent<CandidatePayload> event);
}
