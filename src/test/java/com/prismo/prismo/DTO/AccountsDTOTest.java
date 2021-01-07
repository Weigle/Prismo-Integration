package com.prismo.prismo.DTO;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;

public class AccountsDTOTest {

  @Test
  public void documentNumberFieldNeedNotNullAnnotation() {
    try {
      NotNull prop =
          AccountsDTO.class.getDeclaredField("documentNumber").getAnnotation(NotNull.class);
      assertThat(prop).isNotNull();
      assertThat(prop.message()).isEqualTo("documentNumber can't be null");
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
  }
}
