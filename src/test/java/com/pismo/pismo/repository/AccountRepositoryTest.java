package com.pismo.pismo.repository;

import static org.assertj.core.api.Assertions.*;

import com.pismo.pismo.model.Accounts;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountRepositoryTest {
  private final TestEntityManager entityManager;

  private final AccountRepository accountRepository;

  @Test
  public void whenFindByDocumentNumber_thenReturnAccounts() {
    Accounts account = new Accounts();
    account.setDocumentNumber(123456L);
    entityManager.persist(account);
    entityManager.flush();

    Optional<Accounts> optionalAccountFound =
        accountRepository.findByDocumentNumber(account.getDocumentNumber());

    assertThat(account.getDocumentNumber())
        .isEqualTo(optionalAccountFound.get().getDocumentNumber());
  }

  @Test
  public void save() {
    Accounts account = new Accounts();
    account.setDocumentNumber(123456L);
    Accounts accounts = accountRepository.save(account);

    assertThat(accounts).isNotNull();
  }
}
