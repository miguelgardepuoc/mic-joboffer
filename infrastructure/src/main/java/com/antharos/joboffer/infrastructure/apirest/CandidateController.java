package com.antharos.joboffer.infrastructure.apirest;

import com.antharos.joboffer.application.create.AddCandidateCommand;
import com.antharos.joboffer.application.create.AddCandidateCommandHandler;
import com.antharos.joboffer.application.find.FindCandidateByPersonalEmailQuery;
import com.antharos.joboffer.application.find.FindCandidateByPersonalEmailQueryHandler;
import com.antharos.joboffer.infrastructure.apirest.presentationmodel.AddCandidateRequest;
import com.antharos.joboffer.infrastructure.apirest.presentationmodel.CandidateMapper;
import com.antharos.joboffer.infrastructure.apirest.presentationmodel.CandidateResponse;
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
  private final CandidateMapper mapper;

  @PostMapping
  public ResponseEntity<Void> addCandidate(@RequestBody AddCandidateRequest request) {
    AddCandidateCommand command =
        AddCandidateCommand.builder()
            .id(request.getId())
            .jobOfferId(request.getJobOfferId())
            .personalEmail(request.getPersonalEmail())
            .cvUrl(request.getCvUrl())
            .createdBy("admin")
            .build();

    this.addCandidateCommandHandler.doHandle(command);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<CandidateResponse> findByPersonalEmail(@PathVariable("email") String email) {
    var candidate =
        this.findByPersonalEmailQueryHandler.handle(FindCandidateByPersonalEmailQuery.of(email));

    if (candidate == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(this.mapper.toCandidateResponse(candidate));
  }
}
