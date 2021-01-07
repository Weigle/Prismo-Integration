package com.prismo.prismo.handle;

import com.prismo.prismo.exception.AccountNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class AccountsHanlder extends RuntimeException {
  @ExceptionHandler(AccountNotFound.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public String entityNotFoundHandler(AccountNotFound e) {
    return "ACCOUNTS NOT FOUND";
  }
}
