package com.pismo.pismo.repository;

import com.pismo.pismo.model.Accounts;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Accounts, Long> {
  Optional<Accounts> findByDocumentNumber(Long idAccount);
}
