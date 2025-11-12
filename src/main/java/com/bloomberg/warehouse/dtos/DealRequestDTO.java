package com.bloomberg.warehouse.dtos;

import com.bloomberg.warehouse.annotations.ValidCurrency;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class DealRequestDTO {

    @NotNull
    @Positive(message = "ID must be a positive number")
    private Long id;

    @NotNull(message = "Timestamp is required")
    private Instant dealTimestamp;

    @ValidCurrency
    @NotBlank(message = "From currency is required")
    @Size(min = 3, max = 3, message = "From currency must contain exactly 3 characters and recognized by a recognized ISO 4217 code")
    private String fromCurrency;

    @ValidCurrency
    @NotBlank(message = "To currency is required")
    @Size(min = 3, max = 3, message = "To currency must contain exactly 3 characters and recognized by a recognized ISO 4217 code")
    private String toCurrency;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than 0")
    private BigDecimal amount;
}
