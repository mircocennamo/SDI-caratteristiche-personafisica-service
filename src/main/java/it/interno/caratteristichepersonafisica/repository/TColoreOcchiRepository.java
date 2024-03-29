package it.interno.caratteristichepersonafisica.repository;

import it.interno.caratteristichepersonafisica.entity.TColoreOcchi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TColoreOcchiRepository extends JpaRepository<TColoreOcchi, String>, JpaSpecificationExecutor<TColoreOcchi> {


}
