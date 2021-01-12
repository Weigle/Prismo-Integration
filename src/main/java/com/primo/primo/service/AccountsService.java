package com.primo.primo.service;

import com.primo.primo.DTO.AccountsDTO;
import com.primo.primo.exception.AccountExist;
import com.primo.primo.exception.AccountNotFound;
import com.primo.primo.model.Accounts;
import com.primo.primo.repository.AccountRepository;
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
