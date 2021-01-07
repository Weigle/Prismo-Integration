package com.prismo.prismo.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.prismo.prismo.Model.Accounts;
import com.prismo.prismo.Model.OperationsTypes;
import com.prismo.prismo.Model.Transactions;
import java.time.LocalDate;
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
    transactions.setEventDate(LocalDate.of(2020, 1, 7));
    Transactions accounts = transactionsRepository.save(transactions);

    assertThat(accounts).isNotNull();
  }
}
