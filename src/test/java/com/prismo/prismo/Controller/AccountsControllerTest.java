package com.prismo.prismo.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prismo.prismo.DTO.AccountsDTO;
import com.prismo.prismo.Service.AccountsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;

@WebMvcTest(AccountsController.class)
public class AccountsControllerTest {

  private static final String URL = "/Accounts/";
  private static final Long ONE = 1L;

  @Autowired private AccountsController accountsController;

  @MockBean private AccountsService accountsService;

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  public void accountsCreate_ThenSuccess() throws Exception {
    AccountsDTO accountDTO = AccountsDTO.builder().documentNumber(ONE).build();

    mockMvc
        .perform(
            post(URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(accountDTO)))
        .andExpect(status().isOk());
  }

  @Test
  public void accountsCreateWithoutDocumentNumber_ThenError() throws Exception {
    AccountsDTO accountDTO = AccountsDTO.builder().build();

    mockMvc
        .perform(
            post(URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(accountDTO)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void getAccounts_ThenSuccess() throws Exception {
    AccountsDTO accountDTO = AccountsDTO.builder().documentNumber(ONE).build();

    when(accountsService.getAccounts(ONE)).thenReturn(accountDTO);

    String response =
        mockMvc
            .perform(get(URL + "/{id}", ONE))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    AccountsDTO responseAccounts = objectMapper.readValue(response, AccountsDTO.class);
    assertThat(responseAccounts).isEqualTo(accountDTO);
  }

  @Test
  public void accountsControllerClassNeedRestControllerAnnotation_thenSuccess() {
    assertThat(AccountsController.class.isAnnotationPresent(RestController.class)).isEqualTo(true);
  }
}
