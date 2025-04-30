package com.antharos.joboffer.infrastructure.out.repository.candidate;

import com.antharos.joboffer.domain.candidate.CandidateStatus;
import com.antharos.joboffer.infrastructure.out.repository.joboffer.JobOfferEntity;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

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

  @Column(nullable = false)
  @Enumerated
  @JdbcType(PostgreSQLEnumJdbcType.class)
  private CandidateStatus status;

  @Column(name = "personal_email", nullable = false)
  private String personalEmail;

  @Column(name = "cv_filename", nullable = false)
  private String cvFilename;

  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

  @Column(name = "phone_number", length = 20)
  private String phoneNumber;

  @Column(name = "created_by", nullable = false)
  private String createdBy;

  @Column(name = "created_at", nullable = false)
  private Date createdAt;

  @Column(name = "last_modified_by")
  private String lastModifiedBy;

  @Column(name = "last_modified_at")
  private Date lastModifiedAt;

  @PrePersist
  protected void onCreate() {
    createdAt = new Date();
  }
}
