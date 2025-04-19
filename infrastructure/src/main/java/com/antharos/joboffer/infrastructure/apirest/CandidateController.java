package com.antharos.joboffer.infrastructure.apirest;

import com.antharos.joboffer.application.create.AddCandidateCommand;
import com.antharos.joboffer.application.create.AddCandidateCommandHandler;
import com.antharos.joboffer.application.find.FindCandidateByPersonalEmailQuery;
import com.antharos.joboffer.application.find.FindCandidateByPersonalEmailQueryHandler;
import com.antharos.joboffer.application.find.FindCandidatesByJobOfferQuery;
import com.antharos.joboffer.application.find.FindCandidatesByJobOfferQueryHandler;
import com.antharos.joboffer.application.update.*;
import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.infrastructure.apirest.presentationmodel.candidate.AddCandidateRequest;
import com.antharos.joboffer.infrastructure.apirest.presentationmodel.candidate.CandidateMapper;
import com.antharos.joboffer.infrastructure.apirest.presentationmodel.candidate.CandidateResponse;
import com.antharos.joboffer.infrastructure.apirest.presentationmodel.candidate.SimpleCandidateResponse;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
public class CandidateController {

  private final AddCandidateCommandHandler addCandidateCommandHandler;
  private final FindCandidateByPersonalEmailQueryHandler findByPersonalEmailQueryHandler;
  private final FindCandidatesByJobOfferQueryHandler findCandidatesByJobOfferQueryHandler;
  private final RejectCandidateCommandHandler rejectCandidateCommandHandler;
  private final HireCandidateCommandHandler hireCandidateCommandHandler;
  private final InterviewCandidateCommandHandler interviewCandidateCommandHandler;
  private final CandidateMapper mapper;

  @PostMapping
  public ResponseEntity<Void> addCandidate(@RequestBody AddCandidateRequest request) {
    AddCandidateCommand command =
        AddCandidateCommand.builder()
            .id(request.getId())
            .jobOfferId(request.getJobOfferId())
            .personalEmail(request.getPersonalEmail())
            .cvFilename(request.getCvFilename())
            .createdBy("admin")
            .build();

    this.addCandidateCommandHandler.doHandle(command);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<CandidateResponse> findByPersonalEmail(
      @PathVariable("email") String email) {
    var candidate =
        this.findByPersonalEmailQueryHandler.handle(FindCandidateByPersonalEmailQuery.of(email));

    if (candidate == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(this.mapper.toCandidateResponse(candidate));
  }

  @GetMapping
  public ResponseEntity<List<SimpleCandidateResponse>> findByJobOfferId(
      @RequestParam UUID jobOfferId) {
    List<Candidate> candidates =
        this.findCandidatesByJobOfferQueryHandler.handle(
            FindCandidatesByJobOfferQuery.of(jobOfferId));

    if (candidates.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(this.mapper.toSimpleCandidatesResponse(candidates));
  }

  @PatchMapping("/{candidateId}/reject")
  public ResponseEntity<Void> rejectCandidate(@PathVariable String candidateId) {
    this.rejectCandidateCommandHandler.doHandle(
        RejectCandidateCommand.builder().candidateId(candidateId).byUser("admin").build());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PatchMapping("/{candidateId}/hire")
  public ResponseEntity<Void> hireCandidate(@PathVariable String candidateId) {
    this.hireCandidateCommandHandler.doHandle(
        HireCandidateCommand.builder().candidateId(candidateId).byUser("admin").build());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PatchMapping("/{candidateId}/interview")
  public ResponseEntity<Void> interviewCandidate(@PathVariable String candidateId) {
    this.interviewCandidateCommandHandler.doHandle(
        InterviewCandidateCommand.builder().candidateId(candidateId).byUser("admin").build());
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
