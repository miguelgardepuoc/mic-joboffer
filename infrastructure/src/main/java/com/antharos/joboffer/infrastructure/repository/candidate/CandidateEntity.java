package com.antharos.joboffer.infrastructure.repository.candidate;

import com.antharos.joboffer.domain.candidate.CandidateStatus;
import com.antharos.joboffer.infrastructure.repository.joboffer.JobOfferEntity;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Entity
@Table(name = "candidate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateEntity {

  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "job_offer_id", nullable = false)
  private JobOfferEntity jobOffer;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private CandidateStatus status;

  @Column(name = "personal_email", nullable = false)
  private Long personalEmail;

  @Column(name = "cv", nullable = false)
  private Long cv;

  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

  @Column(name = "phone_number")
  private Long phoneNumber;

  @Column(name = "created_by", nullable = false)
  private String createdBy;

  @Column(name = "created_at", nullable = false)
  private Date createdAt;

  @Column(name = "last_modified_by")
  private String lastModifiedBy;

  @Column(name = "last_modified_at")
  private Date lastModifiedAt;
}
