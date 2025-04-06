package com.antharos.joboffer.infrastructure.apirest;

import com.antharos.joboffer.application.find.FindJobOffersQuery;
import com.antharos.joboffer.application.find.FindJobOffersQueryHandler;
import com.antharos.joboffer.infrastructure.apirest.presentationmodel.JobOfferMapper;
import com.antharos.joboffer.infrastructure.apirest.presentationmodel.SimpleJobOffer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job-offers")
@RequiredArgsConstructor
public class JobOfferController {

  private final FindJobOffersQueryHandler findJobOffersQueryHandler;

  private final JobOfferMapper mapper;

  @GetMapping
  public ResponseEntity<List<SimpleJobOffer>> getAllJobOffers() {
    return ResponseEntity.ok(
        this.mapper.toSimpleJobOffers(
            this.findJobOffersQueryHandler.handle(FindJobOffersQuery.of()).stream().toList()));
  }
}
