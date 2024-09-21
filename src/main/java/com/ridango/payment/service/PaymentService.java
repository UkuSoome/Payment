package com.ridango.payment.service;

import com.ridango.payment.exception.NotFoundException;
import com.ridango.payment.model.entity.Account;
import com.ridango.payment.model.entity.Payment;
import com.ridango.payment.model.PaymentRequest;
import com.ridango.payment.repository.AccountRepository;
import com.ridango.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;


    public void payment(PaymentRequest paymentRequest) {
        Payment payment = new Payment(paymentRequest);

        Account receiver = accountRepository.findById(Long.valueOf(paymentRequest.getReceiverAccountId()))
                .orElseThrow(() -> new NotFoundException("Account not found."));
        Account sender = accountRepository.findById(Long.valueOf(paymentRequest.getSenderAccountId()))
                .orElseThrow(() -> new NotFoundException("Account not found."));

        if (sender.getBalance().compareTo(paymentRequest.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient funds.");
        }

        savePaymentAndAccountBalances(payment, sender, receiver, paymentRequest);
    }

    @Transactional
    private void savePaymentAndAccountBalances(Payment payment, Account sender, Account receiver, PaymentRequest paymentRequest) {
        sender.setBalance(sender.getBalance().subtract(paymentRequest.getAmount()));
        receiver.setBalance(receiver.getBalance().add(paymentRequest.getAmount()));

        accountRepository.save(sender);
        accountRepository.save(receiver);
        paymentRepository.save(payment);
    }

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}
