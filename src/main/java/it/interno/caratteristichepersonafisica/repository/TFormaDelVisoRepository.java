package it.interno.caratteristichepersonafisica.repository;

import it.interno.caratteristichepersonafisica.entity.TFormaDelViso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TFormaDelVisoRepository extends JpaRepository<TFormaDelViso, String>, JpaSpecificationExecutor<TFormaDelViso> {


}
