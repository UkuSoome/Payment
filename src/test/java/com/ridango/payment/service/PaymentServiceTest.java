package com.ridango.payment.service;

import com.ridango.payment.exception.NotFoundException;
import com.ridango.payment.handler.TransactionalDatabaseHandler;
import com.ridango.payment.model.PaymentRequest;
import com.ridango.payment.model.entity.Account;
import com.ridango.payment.repository.AccountRepository;
import com.ridango.payment.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @Mock private PaymentRepository paymentRepository;
    @Mock private AccountRepository accountRepository;
    @Mock TransactionalDatabaseHandler transactionalDatabaseHandler;
    @InjectMocks PaymentService paymentService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void paymentWorksAsExpected() {
        PaymentRequest paymentRequest = new PaymentRequest("1", "2", new BigDecimal("50.00"));

        Account sender = new Account(1L, "name", new BigDecimal("100.00"));
        Account receiver = new Account(2L, "name2", new BigDecimal("100.00"));

        when(accountRepository.findById(sender.getId())).thenReturn(Optional.of(sender));
        when(accountRepository.findById(receiver.getId())).thenReturn(Optional.of(receiver));

        paymentService.payment(paymentRequest);

        assertEquals(sender.getBalance().compareTo(new BigDecimal("50.00")), 0); //0 means it's equal.
        assertEquals(receiver.getBalance().compareTo(new BigDecimal("150.00")), 0);
    }

    @Test
    void paymentThrowsNotFound_when_AccountNotFound() {
        PaymentRequest paymentRequest = new PaymentRequest("1", "2", new BigDecimal("50.00"));

        when(accountRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> paymentService.payment(paymentRequest));
    }

    @Test
    void paymentThrowsIllegalArgument_when_BalanceSmallerThanRequestAmount() {
        PaymentRequest paymentRequest = new PaymentRequest("1", "2", new BigDecimal("100.00"));

        Account sender = new Account(1L, "name", new BigDecimal("50.00"));
        Account receiver = new Account(2L, "name2", new BigDecimal("100.00"));

        when(accountRepository.findById(sender.getId())).thenReturn(Optional.of(sender));
        when(accountRepository.findById(receiver.getId())).thenReturn(Optional.of(receiver));

        assertThrows(IllegalArgumentException.class,
                () -> paymentService.payment(paymentRequest));
    }
}