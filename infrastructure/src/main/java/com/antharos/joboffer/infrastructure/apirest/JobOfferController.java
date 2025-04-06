package com.antharos.joboffer.infrastructure.apirest;

import com.antharos.joboffer.domain.joboffer.JobOffer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job-offers")
@RequiredArgsConstructor
public class JobOfferController {

  @GetMapping
  public ResponseEntity<List<JobOffer>> getAllJobOffers() {
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
