package com.primo.primo.service;

import com.primo.primo.DTO.TransactionsDTO;
import com.primo.primo.exception.AccountNotFound;
import com.primo.primo.model.OperationsTypes;
import com.primo.primo.model.Transactions;
import com.primo.primo.repository.AccountRepository;
import com.primo.primo.repository.TransactionsRepository;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class TransactionsService {

  private final TransactionsRepository transactionsRepository;
  private final AccountRepository accountRepository;

  public void saveTransactions(TransactionsDTO transactionsDTO) throws AccountNotFound {

    transactionsDTO.setAmount(verifyTypeTransaction(transactionsDTO));

    Transactions transactions =
        Transactions.builder()
            .account(
                accountRepository
                    .findById(transactionsDTO.getAccountId())
                    .orElseThrow(AccountNotFound::new))
            .amount(transactionsDTO.getAmount())
            .operationsTypes(transactionsDTO.getOperationTypeId())
            .eventDate(LocalDateTime.now())
            .build();

    transactionsRepository.save(transactions);
  }

  public Long verifyTypeTransaction(TransactionsDTO transactionsDTO) {
    if (!OperationsTypes.PAYMENT.equals(transactionsDTO.getOperationTypeId())) {
      return transactionsDTO.getAmount() * -1;
    }
    return transactionsDTO.getAmount();
  }
}
