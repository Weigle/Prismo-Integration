package com.primo.primo.model;

import static javax.persistence.GenerationType.AUTO;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {
  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;

  @OneToOne private Accounts account;
  private OperationsTypes operationsTypes;
  private Long amount;
  private LocalDateTime eventDate;
}
