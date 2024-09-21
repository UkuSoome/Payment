package com.ridango.payment.model.entity;

import com.ridango.payment.model.PaymentRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@RequiredArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long senderAccountId;
    private Long receiverAccountId;
    private BigDecimal amount;
    private Instant timestamp;

    public Payment(PaymentRequest request) {
        this.senderAccountId = Long.valueOf(request.getSenderAccountId());
        this.receiverAccountId = Long.valueOf(request.getReceiverAccountId());
        this.amount = request.getAmount();
        this.timestamp = Instant.now();
    }
}
