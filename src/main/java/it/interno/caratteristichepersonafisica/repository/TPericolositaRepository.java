package it.interno.caratteristichepersonafisica.repository;

import it.interno.caratteristichepersonafisica.entity.TPericolosita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TPericolositaRepository extends JpaRepository<TPericolosita, String>, JpaSpecificationExecutor<TPericolosita> {


}
