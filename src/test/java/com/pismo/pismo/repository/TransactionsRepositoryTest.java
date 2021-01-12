package com.pismo.pismo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.pismo.pismo.model.Accounts;
import com.pismo.pismo.model.OperationsTypes;
import com.pismo.pismo.model.Transactions;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionsRepositoryTest {
  static final Long ONE = 1L;

  private final TestEntityManager entityManager;

  private final TransactionsRepository transactionsRepository;

  @Test
  public void save() {
    Transactions transactions = new Transactions();
    transactions.setAmount(ONE);
    transactions.setOperationsTypes(OperationsTypes.PAYMENT);
    transactions.setAccount(new Accounts());
    transactions.setEventDate(LocalDateTime.of(2020, 1, 7, 0, 0));
    Transactions accounts = transactionsRepository.save(transactions);

    assertThat(accounts).isNotNull();
  }
}
