package com.bloomberg.warehouse.entites;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "fx_deal")
@ToString
@Getter
@Setter
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Deal {
    @Id
    private Long id;

    @Column(name="from_currency", nullable = false, length = 3)
    private String fromCurrency;

    @Column(name="to_currency", nullable = false, length = 3)
    private String toCurrency;

    @Column(name="deal_timestamp", nullable = false)
    private Instant dealTimestamp;

    @Column(name="amount", nullable = false, precision = 20, scale = 6)
    private BigDecimal amount;

    @Column(name="created_at", nullable = false)
    private Instant createdAt = Instant.now();
}