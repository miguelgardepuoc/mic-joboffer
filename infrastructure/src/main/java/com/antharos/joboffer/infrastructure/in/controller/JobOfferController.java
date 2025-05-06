package com.antharos.joboffer.infrastructure.in.controller;

import com.antharos.joboffer.application.create.AddJobOfferCommand;
import com.antharos.joboffer.application.create.AddJobOfferCommandHandler;
import com.antharos.joboffer.application.delete.WithdrawJobOfferCommand;
import com.antharos.joboffer.application.delete.WithdrawJobOfferCommandHandler;
import com.antharos.joboffer.application.find.FindJobOfferQuery;
import com.antharos.joboffer.application.find.FindJobOfferQueryHandler;
import com.antharos.joboffer.application.find.FindJobOffersQuery;
import com.antharos.joboffer.application.find.FindJobOffersQueryHandler;
import com.antharos.joboffer.application.update.UpdateJobOfferCommand;
import com.antharos.joboffer.application.update.UpdateJobOfferCommandHandler;
import com.antharos.joboffer.infrastructure.in.dto.joboffer.*;
import com.antharos.joboffer.infrastructure.security.ManagementOnly;
import jakarta.annotation.security.PermitAll;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job-offers")
@RequiredArgsConstructor
public class JobOfferController {

  private final FindJobOffersQueryHandler findJobOffersQueryHandler;
  private final FindJobOfferQueryHandler findJobOfferQueryHandler;

  private final AddJobOfferCommandHandler addJobOfferCommandHandler;
  private final UpdateJobOfferCommandHandler updateJobOfferCommandHandler;
  private final WithdrawJobOfferCommandHandler withdrawJobOfferCommandHandler;

  private final JobOfferMapper mapper;

  @PermitAll
  @GetMapping
  public ResponseEntity<List<SimpleJobOffer>> getAllJobOffers() {
    return ResponseEntity.ok(
        this.mapper.toSimpleJobOffers(
            this.findJobOffersQueryHandler.handle(FindJobOffersQuery.of()).stream().toList()));
  }

  @PermitAll
  @GetMapping("/{jobOfferId}")
  public ResponseEntity<JobOfferResponse> findJobOfferDetail(@PathVariable UUID jobOfferId) {
    var jobOffer = this.findJobOfferQueryHandler.handle(FindJobOfferQuery.of(jobOfferId));

    if (jobOffer == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(this.mapper.toJobOfferResponse(jobOffer));
  }

  @ManagementOnly
  @PostMapping
  public ResponseEntity<Void> addJobOffer(@RequestBody AddJobOfferRequest request) {
    AddJobOfferCommand command =
        AddJobOfferCommand.builder()
            .id(request.getId())
            .jobTitleId(request.getJobTitleId())
            .description(request.getDescription())
            .remote(request.getRemote())
            .requirement(request.getRequirement())
            .minSalary(request.getMinSalary())
            .maxSalary(request.getMaxSalary())
            .createdBy("admin")
            .build();

    this.addJobOfferCommandHandler.doHandle(command);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @ManagementOnly
  @PutMapping
  public ResponseEntity<Void> updateJobOffer(@RequestBody UpdateJobOfferRequest request) {
    UpdateJobOfferCommand command =
        UpdateJobOfferCommand.builder()
            .id(request.getId())
            .description(request.getDescription())
            .remote(request.getRemote())
            .requirement(request.getRequirement())
            .minSalary(request.getMinSalary())
            .maxSalary(request.getMaxSalary())
            .lastModifiedBy("admin")
            .build();

    this.updateJobOfferCommandHandler.doHandle(command);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @ManagementOnly
  @DeleteMapping("/{jobOfferId}")
  public ResponseEntity<Void> withdrawJobOffer(@PathVariable String jobOfferId) {
    WithdrawJobOfferCommand command =
        WithdrawJobOfferCommand.builder().id(jobOfferId).lastModifiedBy("admin").build();

    this.withdrawJobOfferCommandHandler.doHandle(command);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
