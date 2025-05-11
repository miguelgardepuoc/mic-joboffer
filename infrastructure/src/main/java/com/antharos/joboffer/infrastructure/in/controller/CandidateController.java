package com.antharos.joboffer.infrastructure.in.controller;

import com.antharos.joboffer.application.create.AddCandidateCommand;
import com.antharos.joboffer.application.create.AddCandidateCommandHandler;
import com.antharos.joboffer.application.find.*;
import com.antharos.joboffer.application.update.*;
import com.antharos.joboffer.domain.candidate.Candidate;
import com.antharos.joboffer.infrastructure.in.dto.candidate.AddCandidateRequest;
import com.antharos.joboffer.infrastructure.in.dto.candidate.CandidateMapper;
import com.antharos.joboffer.infrastructure.in.dto.candidate.CandidateResponse;
import com.antharos.joboffer.infrastructure.in.dto.candidate.SimpleCandidateResponse;
import com.antharos.joboffer.infrastructure.in.util.AuditorUtils;
import com.antharos.joboffer.infrastructure.security.ManagementOnly;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
@Tag(name = "Candidate", description = "Operations related to job offer candidates")
public class CandidateController {

  private final FindCandidateByIdQueryHandler findCandidateByIdQueryHandler;
  private final FindCandidateByPersonalEmailQueryHandler findByPersonalEmailQueryHandler;
  private final FindCandidatesByJobOfferQueryHandler findCandidatesByJobOfferQueryHandler;

  private final AddCandidateCommandHandler addCandidateCommandHandler;
  private final RejectCandidateCommandHandler rejectCandidateCommandHandler;
  private final HireCandidateCommandHandler hireCandidateCommandHandler;
  private final InterviewCandidateCommandHandler interviewCandidateCommandHandler;

  private final CandidateMapper mapper;

  @PermitAll
  @PostMapping
  @Operation(
      summary = "Add a new candidate",
      description = "Registers a new candidate for a job offer")
  @ApiResponse(responseCode = "201", description = "Candidate successfully added")
  public ResponseEntity<Void> addCandidate(@RequestBody AddCandidateRequest request) {
    AddCandidateCommand command =
        AddCandidateCommand.builder()
            .id(request.getId())
            .jobOfferId(request.getJobOfferId())
            .personalEmail(request.getPersonalEmail())
            .cvFilename(request.getCvFilename())
            .createdBy(AuditorUtils.getCurrentUsername())
            .build();

    this.addCandidateCommandHandler.doHandle(command);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @ManagementOnly
  @GetMapping("/{candidateId}")
  @Operation(
      summary = "Get candidate by ID",
      description = "Retrieve full details of a candidate by ID")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Candidate found",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = CandidateResponse.class))),
    @ApiResponse(responseCode = "404", description = "Candidate not found")
  })
  public ResponseEntity<CandidateResponse> findCandidate(@PathVariable String candidateId) {
    var candidate =
        this.findCandidateByIdQueryHandler.handle(FindCandidateByIdQuery.of(candidateId));

    if (candidate == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(this.mapper.toCandidateResponse(candidate));
  }

  @ManagementOnly
  @GetMapping("/email/{email}")
  @Operation(
      summary = "Get candidate by personal email",
      description = "Retrieve a candidate using their personal email")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Candidate found",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = CandidateResponse.class))),
    @ApiResponse(responseCode = "404", description = "Candidate not found")
  })
  public ResponseEntity<CandidateResponse> findByPersonalEmail(
      @PathVariable("email") String email) {
    var candidate =
        this.findByPersonalEmailQueryHandler.handle(FindCandidateByPersonalEmailQuery.of(email));

    if (candidate == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(this.mapper.toCandidateResponse(candidate));
  }

  @ManagementOnly
  @GetMapping
  @Operation(
      summary = "Get candidates by job offer",
      description = "List all candidates who applied to a given job offer")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Candidates found",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SimpleCandidateResponse.class))),
    @ApiResponse(responseCode = "204", description = "No candidates found")
  })
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

  @ManagementOnly
  @PatchMapping("/{candidateId}/reject")
  @Operation(
      summary = "Reject candidate",
      description = "Rejects a candidate from a job offer process")
  @ApiResponse(responseCode = "200", description = "Candidate rejected successfully")
  public ResponseEntity<Void> rejectCandidate(@PathVariable String candidateId) {
    this.rejectCandidateCommandHandler.doHandle(
        RejectCandidateCommand.builder()
            .candidateId(candidateId)
            .byUser(AuditorUtils.getCurrentUsername())
            .build());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @ManagementOnly
  @PatchMapping("/{candidateId}/hire")
  @Operation(summary = "Hire candidate", description = "Marks a candidate as hired")
  @ApiResponse(responseCode = "200", description = "Candidate hired successfully")
  public ResponseEntity<Void> hireCandidate(@PathVariable String candidateId) {
    this.hireCandidateCommandHandler.doHandle(
        HireCandidateCommand.builder()
            .candidateId(candidateId)
            .byUser(AuditorUtils.getCurrentUsername())
            .build());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @ManagementOnly
  @PatchMapping("/{candidateId}/interview")
  @Operation(summary = "Interview candidate", description = "Marks a candidate as interviewed")
  @ApiResponse(responseCode = "200", description = "Candidate interviewed successfully")
  public ResponseEntity<Void> interviewCandidate(@PathVariable String candidateId) {
    this.interviewCandidateCommandHandler.doHandle(
        InterviewCandidateCommand.builder()
            .candidateId(candidateId)
            .byUser(AuditorUtils.getCurrentUsername())
            .build());
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
