package com.ridango.payment.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PaymentRequest {

    private String senderAccountId;
    private String receiverAccountId;
    private BigDecimal amount;

}
