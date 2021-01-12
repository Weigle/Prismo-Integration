package com.primo.primo.repository;

import com.primo.primo.model.Accounts;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Accounts, Long> {
  Optional<Accounts> findByDocumentNumber(Long idAccount);
}
