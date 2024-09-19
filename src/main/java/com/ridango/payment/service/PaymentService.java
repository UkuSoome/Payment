package com.ridango.payment.service;

import com.ridango.payment.model.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    public void payment(PaymentRequest paymentRequest) {
        System.out.println(paymentRequest);
    }
}
