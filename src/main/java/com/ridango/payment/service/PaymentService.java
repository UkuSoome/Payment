package com.ridango.payment.service;

import com.ridango.payment.exception.NotFoundException;
import com.ridango.payment.handler.TransactionalDatabaseHandler;
import com.ridango.payment.model.entity.Account;
import com.ridango.payment.model.entity.Payment;
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
    private final TransactionalDatabaseHandler transactionalDatabaseHandler;

    public void payment(PaymentRequest paymentRequest) {
        Payment payment = new Payment(paymentRequest);

        Account receiver = accountRepository.findById(Long.valueOf(paymentRequest.getReceiverAccountId()))
                .orElseThrow(() -> new NotFoundException("Account not found."));
        Account sender = accountRepository.findById(Long.valueOf(paymentRequest.getSenderAccountId()))
                .orElseThrow(() -> new NotFoundException("Account not found."));

        if (sender.getBalance().compareTo(paymentRequest.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient funds.");
        }

        sender.setBalance(sender.getBalance().subtract(paymentRequest.getAmount()));
        receiver.setBalance(receiver.getBalance().add(paymentRequest.getAmount()));

        transactionalDatabaseHandler.savePaymentAndAccountsTransaction(payment, sender, receiver);
    }

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}
