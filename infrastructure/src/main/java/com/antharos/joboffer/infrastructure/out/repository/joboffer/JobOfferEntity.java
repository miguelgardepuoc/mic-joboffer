package com.antharos.joboffer.infrastructure.out.repository.joboffer;

import com.antharos.joboffer.infrastructure.out.repository.candidate.CandidateEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

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

  @Column(name = "description", nullable = false, columnDefinition = "TEXT")
  private String description;

  @Embedded private SalaryRangeEmbeddable salaryRange;

  @Column(name = "remote", nullable = false)
  private short remote; // 0–100 representation

  @Column(name = "requirement", nullable = false, columnDefinition = "TEXT")
  private String requirement;

  @Column(name = "is_active", nullable = false)
  private boolean isActive;

  @Column(name = "created_by", nullable = false)
  private String createdBy;

  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt;

  @Column(name = "last_modified_by")
  private String lastModifiedBy;

  @Column(name = "last_modified_at")
  private LocalDate lastModifiedAt;

  @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CandidateEntity> candidates = new ArrayList<>();

  public JobOfferEntity(UUID id) {
    this.id = id;
  }

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDate.now();
  }

  @PreUpdate
  protected void onUpdate() {
    lastModifiedAt = LocalDate.now();
  }
}
