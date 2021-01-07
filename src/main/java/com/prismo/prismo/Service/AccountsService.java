package com.prismo.prismo.Service;

import com.prismo.prismo.DTO.AccountsDTO;
import com.prismo.prismo.Model.Accounts;
import com.prismo.prismo.Repository.AccountRepository;
import com.prismo.prismo.exception.AccountNotFound;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountsService {
  private final AccountRepository accountRepository;

  public void saveAccount(AccountsDTO accountsDTO) {
    Accounts newAccount =
        Accounts.builder().documentNumber(accountsDTO.getDocumentNumber()).build();
    accountRepository.save(newAccount);
  }

  public AccountsDTO getAccounts(Long idAccount) throws AccountNotFound {
    Optional<Accounts> accounts = accountRepository.findByDocumentNumber(idAccount);
    if (!accounts.isPresent()) {
      throw new AccountNotFound();
    }
    AccountsDTO accountsDTO = new AccountsDTO();
    return accountsDTO.accountsToAccountsDTO(accounts.orElse(new Accounts()));
  }
}
