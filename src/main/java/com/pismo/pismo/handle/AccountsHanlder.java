package com.pismo.pismo.handle;

import static org.springframework.http.HttpStatus.*;

import com.pismo.pismo.exception.AccountExist;
import com.pismo.pismo.exception.AccountNotFound;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class AccountsHanlder extends RuntimeException {
  private static final String ENTITY_NOT_FOUND_HANDLER_RESPONSE = "ACCOUNTS NOT FOUND";
  private static final String ACCOUNT_EXIST = "THIS IS ACCOUNT ALREADY EXISTS";

  @ExceptionHandler(AccountNotFound.class)
  @ResponseStatus(value = BAD_REQUEST)
  public String entityNotFoundHandler(AccountNotFound e) {
    return ENTITY_NOT_FOUND_HANDLER_RESPONSE;
  }

  @ExceptionHandler(AccountExist.class)
  @ResponseStatus(value = BAD_REQUEST)
  public String accountExist(AccountExist e) {
    return ACCOUNT_EXIST;
  }
}
