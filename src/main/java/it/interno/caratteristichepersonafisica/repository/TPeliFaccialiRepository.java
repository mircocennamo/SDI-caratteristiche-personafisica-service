package it.interno.caratteristichepersonafisica.repository;

import it.interno.caratteristichepersonafisica.entity.TPeliFacciali;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TPeliFaccialiRepository extends JpaRepository<TPeliFacciali, String>, JpaSpecificationExecutor<TPeliFacciali> {


}
