package it.interno.caratteristichepersonafisica.repository;

import it.interno.caratteristichepersonafisica.entity.TCarnagione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TCarnagioneRepository extends JpaRepository<TCarnagione, String>, JpaSpecificationExecutor<TCarnagione> {


}
