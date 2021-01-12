package com.pismo.pismo.service;

import static java.util.Optional.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

import com.pismo.pismo.DTO.AccountsDTO;
import com.pismo.pismo.exception.AccountExist;
import com.pismo.pismo.exception.AccountNotFound;
import com.pismo.pismo.model.Accounts;
import com.pismo.pismo.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@SpringBootTest
public class AccountsServiceTest {
  static Long ONE = 1L;

  @InjectMocks private AccountsService accountsService;
  @Mock private AccountRepository accountRepository;

  @Captor private ArgumentCaptor<Accounts> accountsCaptor;

  @Test
  void getAccountTest_ThenSuccess() throws AccountNotFound {
    Accounts accounts = new Accounts();
    accounts.setId(1L);
    accounts.setDocumentNumber(123L);

    when(accountRepository.findByDocumentNumber(any())).thenReturn(of(accounts));
    AccountsDTO accountsDTO = accountsService.getAccounts(1L);
    assertThat(accountsDTO.getAccountId()).isEqualTo(1L);
    assertThat(accountsDTO.getDocumentNumber()).isEqualTo(123L);
  }

  @Test
  void getAccountTest_ThenError() {
    when(accountRepository.findByDocumentNumber(any())).thenReturn(empty());
    assertThatExceptionOfType(AccountNotFound.class)
        .isThrownBy(() -> accountsService.getAccounts(1L));
  }

  @Test
  void saveAccountTest_ThenSuccess() throws AccountExist {
    AccountsDTO accountsSaveDTO = AccountsDTO.builder().documentNumber(ONE).build();
    accountsService.saveAccount(accountsSaveDTO);
    verify(accountRepository, times(1)).save(accountsCaptor.capture());
    Accounts accountsCaptured = accountsCaptor.getValue();
    assertAll(
        () -> assertThat(accountsCaptured).isNotNull(),
        () -> assertThat(accountsCaptured.getDocumentNumber()).isEqualTo(ONE));
  }

  @Test
  void saveAccountTestWhenExist_ThenError() {
    when(accountRepository.findByDocumentNumber(ONE))
        .thenReturn(java.util.Optional.of(new Accounts()));
    AccountsDTO accountsSaveDTO = AccountsDTO.builder().documentNumber(ONE).build();
    assertThatExceptionOfType(AccountExist.class)
        .isThrownBy(() -> accountsService.saveAccount(accountsSaveDTO));
  }

  @Test
  public void accountsServiceClassNeedServiceAnnotation_thenSuccess() {
    assertThat(AccountsService.class.isAnnotationPresent(Service.class)).isEqualTo(true);
  }
}
