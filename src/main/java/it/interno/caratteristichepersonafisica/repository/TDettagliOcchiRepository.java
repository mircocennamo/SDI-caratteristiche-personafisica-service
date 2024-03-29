package it.interno.caratteristichepersonafisica.repository;

import it.interno.caratteristichepersonafisica.entity.TDettagliOcchi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TDettagliOcchiRepository extends JpaRepository<TDettagliOcchi, String>, JpaSpecificationExecutor<TDettagliOcchi> {


}
