package com.antharos.joboffer.infrastructure.in.event;

import com.antharos.joboffer.application.commands.candidate.updatefullname.UpdateCandidateFullNameCommandHandler;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Profile("!prod")
@Slf4j
@Component
public class MessageConsumerImplLocal extends AbstractMessageConsumer {

  public MessageConsumerImplLocal(UpdateCandidateFullNameCommandHandler handler) {
    super(handler);
  }

  @JmsListener(
      destination = "${consumer.topic.name}",
      containerFactory = "jmsListenerContainerFactory")
  public void onMessage(jakarta.jms.Message message) {
    if (message instanceof TextMessage textMessage) {
      try {
        processMessageText(textMessage.getText());
      } catch (Exception e) {
        log.error("Error reading text message", e);
      }
    } else {
      log.error("Unsupported JMS message type: {}", message.getClass().getName());
    }
  }
}
