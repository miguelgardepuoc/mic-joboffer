package com.antharos.joboffer.infrastructure.event.model;

import com.antharos.joboffer.domain.candidate.Candidate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CandidateSignedUpDomainMessage extends BaseMessage<Candidate> {

  public CandidateSignedUpDomainMessage(String id, String subject, Candidate content) {
    super(id, subject, content);
  }
}
