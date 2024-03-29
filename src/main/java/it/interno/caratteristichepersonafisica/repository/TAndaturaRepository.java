package it.interno.caratteristichepersonafisica.repository;

import it.interno.caratteristichepersonafisica.entity.TAndatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TAndaturaRepository extends JpaRepository<TAndatura, String>, JpaSpecificationExecutor<TAndatura> {

}
