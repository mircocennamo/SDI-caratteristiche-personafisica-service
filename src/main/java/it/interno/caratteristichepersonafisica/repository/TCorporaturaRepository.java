package it.interno.caratteristichepersonafisica.repository;

import it.interno.caratteristichepersonafisica.entity.TCorporatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TCorporaturaRepository extends JpaRepository<TCorporatura, String>, JpaSpecificationExecutor<TCorporatura> {


}
