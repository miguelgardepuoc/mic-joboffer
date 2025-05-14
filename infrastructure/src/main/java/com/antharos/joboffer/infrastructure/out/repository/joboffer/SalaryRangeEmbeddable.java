package com.antharos.joboffer.infrastructure.out.repository.joboffer;

import com.antharos.joboffer.domain.joboffer.valueobject.SalaryRange;
import jakarta.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class SalaryRangeEmbeddable {
  private BigDecimal minSalary;
  private BigDecimal maxSalary;

  public SalaryRange toDomain() {
    return new SalaryRange(minSalary, maxSalary);
  }

  public static SalaryRangeEmbeddable fromDomain(SalaryRange salaryRange) {
    SalaryRangeEmbeddable emb = new SalaryRangeEmbeddable();
    emb.minSalary = salaryRange.min();
    emb.maxSalary = salaryRange.max();
    return emb;
  }
}
