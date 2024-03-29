package it.interno.caratteristichepersonafisica.repository;

import it.interno.caratteristichepersonafisica.entity.TDenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TDentiRepository extends JpaRepository<TDenti, String>, JpaSpecificationExecutor<TDenti> {


}
