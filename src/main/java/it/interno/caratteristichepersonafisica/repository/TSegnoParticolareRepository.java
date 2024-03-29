package it.interno.caratteristichepersonafisica.repository;

import it.interno.caratteristichepersonafisica.entity.TSegnoParticolare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TSegnoParticolareRepository extends JpaRepository<TSegnoParticolare, String>, JpaSpecificationExecutor<TSegnoParticolare> {


}
