package com.antharos.joboffer.domain.joboffer.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class SalaryRangeUnitTest {

  @Test
  void validSalaryRange_shouldCreateObject() {
    SalaryRange range = new SalaryRange(new BigDecimal("3000.00"), new BigDecimal("5000.00"));
    assertEquals(new BigDecimal("3000.00"), range.min());
    assertEquals(new BigDecimal("5000.00"), range.max());
  }

  @Test
  void minSalaryNull_shouldThrowException() {
    NullPointerException exception =
        assertThrows(
            NullPointerException.class, () -> new SalaryRange(null, new BigDecimal("5000.00")));
    assertEquals("Minimum salary must not be null", exception.getMessage());
  }

  @Test
  void maxSalaryNull_shouldThrowException() {
    NullPointerException exception =
        assertThrows(
            NullPointerException.class, () -> new SalaryRange(new BigDecimal("3000.00"), null));
    assertEquals("Maximum salary must not be null", exception.getMessage());
  }

  @Test
  void minSalaryNegative_shouldThrowException() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new SalaryRange(new BigDecimal("-1.00"), new BigDecimal("1000.00")));
    assertEquals("Minimum salary must be non-negative", exception.getMessage());
  }

  @Test
  void maxSalaryLessThanMin_shouldThrowException() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new SalaryRange(new BigDecimal("4000.00"), new BigDecimal("3000.00")));
    assertEquals(
        "Maximum salary must be greater than or equal to minimum salary", exception.getMessage());
  }

  @Test
  void maxSalaryEqualToMin_shouldBeValid() {
    SalaryRange range = new SalaryRange(new BigDecimal("3500.00"), new BigDecimal("3500.00"));
    assertEquals(new BigDecimal("3500.00"), range.min());
    assertEquals(new BigDecimal("3500.00"), range.max());
  }
}
