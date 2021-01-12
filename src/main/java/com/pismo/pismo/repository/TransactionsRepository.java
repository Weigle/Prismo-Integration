package com.pismo.pismo.repository;

import com.pismo.pismo.model.Transactions;
import org.springframework.data.repository.CrudRepository;

public interface TransactionsRepository extends CrudRepository<Transactions, Long> {}
