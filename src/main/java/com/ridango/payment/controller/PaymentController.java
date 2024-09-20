package com.ridango.payment.controller;


import com.ridango.payment.entity.Payment;
import com.ridango.payment.model.PaymentRequest;
import com.ridango.payment.model.Response;
import com.ridango.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping(value = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> payment(@RequestBody PaymentRequest paymentRequest) {
        paymentService.payment(paymentRequest);
        return Response.ok();
    }

    @GetMapping(value = "/payment")
    public ResponseEntity<List<Payment>> payment() {
        List<Payment> payments = paymentService.getPayment();
        return Response.ok(payments);
    }
}
