package com.bloomberg.warehouse.dtos;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Set;

class CurrencyValidatorTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldFailValidationForInvalidCurrency() {
        DealRequestDTO deal = DealRequestDTO.builder()
                .id(1L)
                .dealTimestamp(Instant.now())
                .fromCurrency("XXy")
                .toCurrency("Yab")
                .amount(BigDecimal.TEN)
                .build();

        Set<ConstraintViolation<DealRequestDTO>> violations = validator.validate(deal);

        List<String> failedFields = violations.stream()
                .map(v -> v.getPropertyPath().toString())
                .toList();

        assertThat(failedFields).containsExactlyInAnyOrder("fromCurrency", "toCurrency");
    }

    @Test
    void shouldPassValidationForValidCurrency() {
        DealRequestDTO deal = DealRequestDTO.builder()
                .id(1000L)
                .fromCurrency("usd")
                .toCurrency("EUR")
                .dealTimestamp(java.time.Instant.now())
                .amount(java.math.BigDecimal.valueOf(150.50))
                .build();

        Set<ConstraintViolation<DealRequestDTO>> violations = validator.validate(deal);

        assertThat(violations).isEmpty();
    }
}
