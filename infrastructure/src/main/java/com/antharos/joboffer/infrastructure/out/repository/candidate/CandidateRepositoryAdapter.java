package com.antharos.joboffer.infrastructure.out.repository.candidate;

import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.domain.candidate.CandidateId;
import com.antharos.joboffer.domain.candidate.PersonalEmail;
import com.antharos.joboffer.domain.candidate.repository.CandidateRepository;
import com.antharos.joboffer.domain.joboffer.JobOfferId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CandidateRepositoryAdapter implements CandidateRepository {

  private final JpaCandidateRepository jpaRepository;
  private final CandidateMapper mapper;

  @Override
  public void saveCandidate(Candidate candidate) {
    this.jpaRepository.save(this.mapper.toEntity(candidate));
  }

  @Override
  public Optional<Candidate> findBy(CandidateId candidateId) {
    return this.jpaRepository
        .findById(UUID.fromString(candidateId.getValueAsString()))
        .map(this.mapper::toDomain);
  }

  @Override
  public Optional<Candidate> findByPersonalEmail(String personalEmail) {
    return this.jpaRepository.findByPersonalEmail(personalEmail).map(this.mapper::toDomain);
  }

  @Override
  public List<Candidate> findByJobOfferId(UUID jobOfferId) {
    return this.jpaRepository.findByJobOffer_Id(jobOfferId).stream()
        .map(this.mapper::toDomain)
        .toList();
  }

  @Override
  public boolean existsByEmail(JobOfferId jobOfferId, PersonalEmail personalEmail) {
    return this.jpaRepository.existsByPersonalEmailIgnoreCaseAndJobOffer_Id(
        personalEmail.value(), UUID.fromString(jobOfferId.getValueAsString()));
  }

  @Override
  public Optional<Candidate> findById(String id) {
    return this.jpaRepository.findById(UUID.fromString(id)).map(this.mapper::toDomain);
  }
}
