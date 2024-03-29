package it.interno.caratteristichepersonafisica.repository;

import it.interno.caratteristichepersonafisica.entity.TStileCapelli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TStileCapelliRepository extends JpaRepository<TStileCapelli, String>, JpaSpecificationExecutor<TStileCapelli> {


}
