package com.primo.primo.DTO;

import com.primo.primo.model.Accounts;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDTO {
  private Long accountId;

  @NotNull(message = "documentNumber can't be null")
  private Long documentNumber;

  public AccountsDTO accountsToAccountsDTO(Accounts accounts) {
    return AccountsDTO.builder()
        .documentNumber(accounts.getDocumentNumber())
        .accountId(accounts.getId())
        .build();
  }
}
