package it.interno.caratteristichepersonafisica.repository;

import it.interno.caratteristichepersonafisica.entity.TMento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TMentoRepository extends JpaRepository<TMento, String>, JpaSpecificationExecutor<TMento> {


}
