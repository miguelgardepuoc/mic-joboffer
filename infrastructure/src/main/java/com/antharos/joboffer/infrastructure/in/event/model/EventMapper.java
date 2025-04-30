package com.antharos.joboffer.infrastructure.in.event.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class EventMapper {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static NameInfo mapToNameInfoEvent(String jsonMessage) throws IOException {
    JsonNode root = objectMapper.readTree(jsonMessage);
    JsonNode payload = root.get("payload");
    NameInfo event = new NameInfo();
    event.setCandidateId(payload.get("candidateId").asText());
    event.setName(payload.get("name").asText());
    event.setSurname(payload.get("surname").asText());
    return event;
  }
}
