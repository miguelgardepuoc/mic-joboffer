package com.antharos.joboffer.infrastructure.repository.joboffer;

import com.antharos.joboffer.infrastructure.repository.candidate.CandidateEntity;
import jakarta.persistence.*;
import java.sql.Types;
import java.util.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

@Entity
@Table(name = "job_offer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobOfferEntity {

  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "job_title_id", nullable = false)
  private UUID jobTitleId;

  @Column(name = "description", nullable = false)
  private String description;

  @Embedded private SalaryRangeEmbeddable salaryRange;

  @Column(name = "remote", nullable = false)
  private Short remote; // 0–100 representation

  @Column(name = "requirement", nullable = false)
  private String requirement;

  @Lob
  @Column(name = "photo", nullable = false)
  @JdbcTypeCode(Types.BINARY)
  private byte[] photo;

  @Column(name = "is_active", nullable = false)
  private boolean isActive;

  @Column(name = "created_by", nullable = false)
  private String createdBy;

  @Column(name = "created_at", nullable = false)
  private Date createdAt;

  @Column(name = "last_modified_by")
  private String lastModifiedBy;

  @Column(name = "last_modified_at")
  private Date lastModifiedAt;

  @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CandidateEntity> candidates = new ArrayList<>();
}
