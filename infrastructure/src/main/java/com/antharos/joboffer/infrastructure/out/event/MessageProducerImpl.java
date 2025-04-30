package com.antharos.joboffer.infrastructure.out.event;

import static com.antharos.joboffer.infrastructure.out.event.model.EventNames.CANDIDATE_APPLIED;

import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.domain.candidate.repository.MessageProducer;
import com.antharos.joboffer.infrastructure.out.event.model.BaseEvent;
import com.antharos.joboffer.infrastructure.out.event.model.CandidatePayload;
import com.antharos.joboffer.infrastructure.out.event.model.CandidatePayloadMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Topic;
import java.time.Instant;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageProducerImpl implements MessageProducer {

  private final ConnectionFactory producerConnectionFactory;
  private final ObjectMapper objectMapper;
  private static final String CANDIDATE_AGGREGATE = "Candidate";
  private final CandidatePayloadMapper mapper;

  @Value("${producer.topic.name}")
  private String topicName;

  public MessageProducerImpl(
      ConnectionFactory producerConnectionFactory,
      ObjectMapper objectMapper,
      CandidatePayloadMapper mapper) {
    this.producerConnectionFactory = producerConnectionFactory;
    this.objectMapper = objectMapper;
    this.mapper = mapper;
  }

  public void sendMessage(final BaseEvent<CandidatePayload> event) {
    try (JMSContext context = this.producerConnectionFactory.createContext()) {
      final Topic topic = context.createTopic(this.topicName);

      final String messageJson = this.objectMapper.writeValueAsString(event);

      context.createProducer().send(topic, messageJson);

      log.info("Message sent: {}", messageJson);
    } catch (Exception e) {
      log.error("Error sending message: {} ", event, e);
    }
  }

  @Override
  public void sendCandidateAppliedEvent(Candidate candidate) {
    BaseEvent<CandidatePayload> event =
        new BaseEvent<>(
            UUID.randomUUID().toString(),
            Instant.now(),
            CANDIDATE_APPLIED.getDescription(),
            candidate.getId().getValueAsString(),
            CANDIDATE_AGGREGATE,
            candidate.getCreatedBy(),
            1,
            this.mapper.toPayload(candidate));
    this.sendMessage(event);
  }
}
