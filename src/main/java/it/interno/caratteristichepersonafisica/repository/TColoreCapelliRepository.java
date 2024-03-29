package it.interno.caratteristichepersonafisica.repository;

import it.interno.caratteristichepersonafisica.entity.TColoreCapelli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TColoreCapelliRepository extends JpaRepository<TColoreCapelli, String>, JpaSpecificationExecutor<TColoreCapelli> {


}
