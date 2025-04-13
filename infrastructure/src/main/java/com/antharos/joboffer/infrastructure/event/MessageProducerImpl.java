package com.antharos.joboffer.infrastructure.event;

import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.domain.candidate.repository.MessageProducer;
import com.antharos.joboffer.infrastructure.event.model.CandidateAppliedDomainMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Topic;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageProducerImpl implements MessageProducer {

  private final ConnectionFactory producerConnectionFactory;
  private final ObjectMapper objectMapper;

  @Value("${producer.topic.name}")
  private String topicName;

  public MessageProducerImpl(
      ConnectionFactory producerConnectionFactory, ObjectMapper objectMapper) {
    this.producerConnectionFactory = producerConnectionFactory;
    this.objectMapper = objectMapper;
  }

  public void sendMessage(final UUID id, final String subject, final Candidate candidate) {
    try (JMSContext context = this.producerConnectionFactory.createContext()) {
      final Topic topic = context.createTopic(this.topicName);

      final CandidateAppliedDomainMessage message =
          new CandidateAppliedDomainMessage(id.toString(), subject, candidate);

      final String messageJson = this.objectMapper.writeValueAsString(message);

      context.createProducer().send(topic, messageJson);

      log.info("Message sent: {}", messageJson);
    } catch (Exception e) {
      log.error("Error sending message: {} ", candidate, e);
    }
  }

  @Override
  public void sendCandidateApplied(Candidate candidate) {
    this.sendMessage(
            UUID.fromString(candidate.getId().getValueAsString()), "CANDIDATE_APPLIED", candidate);
  }
}
