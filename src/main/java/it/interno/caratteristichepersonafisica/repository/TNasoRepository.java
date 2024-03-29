package it.interno.caratteristichepersonafisica.repository;

import it.interno.caratteristichepersonafisica.entity.TNaso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TNasoRepository extends JpaRepository<TNaso, String>, JpaSpecificationExecutor<TNaso> {

}
