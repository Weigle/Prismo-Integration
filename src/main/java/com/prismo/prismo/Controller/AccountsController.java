package com.prismo.prismo.Controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.prismo.prismo.DTO.AccountsDTO;
import com.prismo.prismo.Service.AccountsService;
import com.prismo.prismo.exception.AccountNotFound;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Accounts")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountsController {

  private static final String producesType = "application/json";

  private final AccountsService accountsService;

  @RequestMapping(value = "/{idAccount}", method = GET, produces = producesType)
  public AccountsDTO getAccounts(@PathVariable Long idAccount) throws AccountNotFound {
    return accountsService.getAccounts(idAccount);
  }

  @RequestMapping(value = "/", method = POST, produces = producesType)
  public void createAccounts(@Valid @RequestBody AccountsDTO accountsDTO) {
    accountsService.saveAccount(accountsDTO);
  }
}
