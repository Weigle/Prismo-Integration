package com.prismo.prismo.Repository;

import com.prismo.prismo.Model.Accounts;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Accounts, Long> {
  Optional<Accounts> findByDocumentNumber(Long idAccount);
}
