package com.prismo.prismo.Service;

import static com.prismo.prismo.Model.OperationsTypes.*;

import com.prismo.prismo.DTO.TransactionsDTO;
import com.prismo.prismo.Model.Transactions;
import com.prismo.prismo.Repository.AccountRepository;
import com.prismo.prismo.Repository.TransactionsRepository;
import com.prismo.prismo.exception.AccountNotFound;
import java.time.LocalDate;
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
            .eventDate(LocalDate.now())
            .build();

    transactionsRepository.save(transactions);
  }

  public Long verifyTypeTransaction(TransactionsDTO transactionsDTO) {
    if (!PAYMENT.equals(transactionsDTO.getOperationTypeId())) {
      return transactionsDTO.getAmount() * -1;
    }
    return transactionsDTO.getAmount();
  }
}
