package com.ridango.payment.handler;

import com.ridango.payment.model.PaymentRequest;
import com.ridango.payment.model.entity.Account;
import com.ridango.payment.model.entity.Payment;
import com.ridango.payment.repository.AccountRepository;
import com.ridango.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TransactionalDatabaseHandler {
    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public void savePaymentAndAccountsTransaction(Payment payment, Account sender, Account receiver) {
        accountRepository.save(sender);
        accountRepository.save(receiver);
        paymentRepository.save(payment);
    }
}
