package it.interno.caratteristichepersonafisica.repository;

import it.interno.caratteristichepersonafisica.entity.TOrecchie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TOrecchieRepository extends JpaRepository<TOrecchie, String>, JpaSpecificationExecutor<TOrecchie> {


}
