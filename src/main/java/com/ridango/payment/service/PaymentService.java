package com.ridango.payment.service;

import com.ridango.payment.entity.Account;
import com.ridango.payment.entity.Payment;
import com.ridango.payment.model.PaymentRequest;
import com.ridango.payment.repository.AccountRepository;
import com.ridango.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;


    public void payment(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setReceiverAccountId(Long.valueOf(paymentRequest.getReceiverAccountId()));
        payment.setSenderAccountId(Long.valueOf(paymentRequest.getSenderAccountId()));
        paymentRepository.save(payment);
    }

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}
