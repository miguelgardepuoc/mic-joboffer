package com.antharos.joboffer.infrastructure.in.event;

import com.antharos.joboffer.application.commands.candidate.updatefullname.UpdateCandidateFullNameCommand;
import com.antharos.joboffer.application.commands.candidate.updatefullname.UpdateCandidateFullNameCommandHandler;
import com.antharos.joboffer.infrastructure.in.event.model.EventMapper;
import com.antharos.joboffer.infrastructure.in.event.model.NameInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractMessageConsumer {

  protected final UpdateCandidateFullNameCommandHandler commandHandler;

  public AbstractMessageConsumer(UpdateCandidateFullNameCommandHandler commandHandler) {
    this.commandHandler = commandHandler;
  }

  protected void processMessageText(String messageText) {
    try {
      log.info("Processing message: {}", messageText);
      NameInfo event = EventMapper.mapToNameInfoEvent(messageText);

      commandHandler.doHandle(
          UpdateCandidateFullNameCommand.builder()
              .candidateId(event.getCandidateId())
              .name(event.getName())
              .surname(event.getSurname())
              .build());

    } catch (Exception e) {
      log.error("Error processing message: {}", messageText, e);
    }
  }
}
