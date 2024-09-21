package com.ridango.payment.controller;

import com.ridango.payment.model.entity.Account;
import com.ridango.payment.model.entity.Payment;
import com.ridango.payment.model.PaymentRequest;
import com.ridango.payment.model.Response;
import com.ridango.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<Void> payment(@Validated @RequestBody PaymentRequest paymentRequest) {
        paymentService.payment(paymentRequest);
        return Response.ok();
    }

    @GetMapping(value = "/payment")
    public ResponseEntity<List<Payment>> payment() {
        List<Payment> payments = paymentService.getPayments();
        return Response.ok(payments);
    }

    @GetMapping(value = "/account")
    public ResponseEntity<List<Account>> account() {
        List<Account> accounts = paymentService.getAccounts();
        return Response.ok(accounts);
    }
}
