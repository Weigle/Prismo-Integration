package com.primo.primo.DTO;

import com.primo.primo.model.OperationsTypes;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionsDTO {
  @NotNull(message = "accountId can't be null")
  private Long accountId;

  @NotNull(message = "operationTypeId can't be null")
  private OperationsTypes operationTypeId;

  @NotNull(message = "amount can't be null")
  private Long amount;
}
