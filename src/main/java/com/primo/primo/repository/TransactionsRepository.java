package com.primo.primo.repository;

import com.primo.primo.model.Transactions;
import org.springframework.data.repository.CrudRepository;

public interface TransactionsRepository extends CrudRepository<Transactions, Long> {}
