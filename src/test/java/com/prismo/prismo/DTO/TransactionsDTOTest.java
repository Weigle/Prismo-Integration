package com.prismo.prismo.DTO;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;

public class TransactionsDTOTest {

  @Test
  public void accountIdFieldNeedNotNullAnnotation() {
    try {
      NotNull prop =
          TransactionsDTO.class.getDeclaredField("accountId").getAnnotation(NotNull.class);
      assertThat(prop).isNotNull();
      assertThat(prop.message()).isEqualTo("accountId can't be null");
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void operationTypeIdFieldNeedNotNullAnnotation() {
    try {
      NotNull prop =
          TransactionsDTO.class.getDeclaredField("operationTypeId").getAnnotation(NotNull.class);
      assertThat(prop).isNotNull();
      assertThat(prop.message()).isEqualTo("operationTypeId can't be null");
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void amountFieldNeedNotNullAnnotation() {
    try {
      NotNull prop = TransactionsDTO.class.getDeclaredField("amount").getAnnotation(NotNull.class);
      assertThat(prop).isNotNull();
      assertThat(prop.message()).isEqualTo("amount can't be null");
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
  }
}
