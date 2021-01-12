package com.primo.primo.service;

import static java.util.Optional.*;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.primo.primo.DTO.TransactionsDTO;
import com.primo.primo.exception.AccountNotFound;
import com.primo.primo.model.Accounts;
import com.primo.primo.model.OperationsTypes;
import com.primo.primo.model.Transactions;
import com.primo.primo.repository.AccountRepository;
import com.primo.primo.repository.TransactionsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@SpringBootTest
public class TransactionsServiceService {
  static Long ONE = 1L;
  static Long TEN = 10L;
  static Long TEN_NEGATIVE = -10L;
  @InjectMocks private TransactionsService transactionsService;

  @Mock private TransactionsRepository transactionsRepository;

  @Mock private AccountRepository accountRepository;

  @Captor private ArgumentCaptor<Transactions> transactionsCaptor;

  @Test
  void verifyTypeTransactionWithOperationCashPurchase_ThenSuccess() {
    TransactionsDTO transactionsDTO =
        TransactionsDTO.builder()
            .accountId(ONE)
            .amount(TEN)
            .operationTypeId(OperationsTypes.CASH_PURCHASE)
            .build();
    Long result = transactionsService.verifyTypeTransaction(transactionsDTO);
    assertThat(result).isEqualTo(TEN_NEGATIVE);
  }

  @Test
  void verifyTypeTransactionWithOperationPayment_ThenSuccess() {
    TransactionsDTO transactionsDTO =
        TransactionsDTO.builder()
            .accountId(ONE)
            .amount(TEN)
            .operationTypeId(OperationsTypes.PAYMENT)
            .build();
    Long result = transactionsService.verifyTypeTransaction(transactionsDTO);
    assertThat(result).isEqualTo(TEN);
  }

  @Test
  void saveTransaction_ThenSuccess() throws AccountNotFound {
    Accounts accounts = Accounts.builder().documentNumber(TEN).id(ONE).build();
    TransactionsDTO transactionsDTO =
        TransactionsDTO.builder()
            .accountId(ONE)
            .amount(TEN)
            .operationTypeId(OperationsTypes.PAYMENT)
            .build();
    when(accountRepository.findById(any())).thenReturn(of(accounts));
    transactionsService.saveTransactions(transactionsDTO);
    verify(transactionsRepository, times(1)).save(transactionsCaptor.capture());
    Transactions transactions = transactionsCaptor.getValue();
    assertAll(
        () -> assertThat(transactions).isNotNull(),
        () -> assertThat(transactions.getAmount()).isEqualTo(TEN),
        () -> assertThat(transactions.getAccount()).isEqualTo(accounts),
        () -> assertThat(transactions.getEventDate()).isNotNull());
  }

  @Test
  void saveTransaction_ThenError() {
    TransactionsDTO transactionsDTO =
        TransactionsDTO.builder()
            .accountId(ONE)
            .amount(TEN)
            .operationTypeId(OperationsTypes.PAYMENT)
            .build();
    when(accountRepository.findById(any())).thenReturn(empty());
    assertThatExceptionOfType(AccountNotFound.class)
        .isThrownBy(() -> transactionsService.saveTransactions(transactionsDTO));
  }

  @Test
  public void transactionsServiceClassNeedServiceAnnotation_ThenSuccess() {
    assertThat(TransactionsService.class.isAnnotationPresent(Service.class)).isEqualTo(true);
  }
}
