package com.pismo.pismo.service;

import com.pismo.pismo.DTO.AccountsDTO;
import com.pismo.pismo.exception.AccountExist;
import com.pismo.pismo.exception.AccountNotFound;
import com.pismo.pismo.model.Accounts;
import com.pismo.pismo.repository.AccountRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountsService {
  private final AccountRepository accountRepository;

  public void saveAccount(AccountsDTO accountsDTO) throws AccountExist {
    Accounts newAccount =
        Accounts.builder().documentNumber(accountsDTO.getDocumentNumber()).build();
    boolean haveAccount =
        accountRepository.findByDocumentNumber(accountsDTO.getDocumentNumber()).isPresent();

    if (haveAccount) {
      throw new AccountExist();
    }
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
