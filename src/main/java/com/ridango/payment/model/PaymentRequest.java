package com.ridango.payment.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    @NotBlank
    private String senderAccountId;
    @NotBlank
    private String receiverAccountId;
    @DecimalMin(value = "0")
    @Digits(integer = 100, fraction = 2)
    private BigDecimal amount;
}
