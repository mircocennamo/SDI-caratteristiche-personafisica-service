package it.interno.caratteristichepersonafisica.repository.specifications;

import it.interno.caratteristichepersonafisica.entity.TCorporatura;
import it.interno.caratteristichepersonafisica.entity.TCorporatura_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TCorporaturaSpecifications {

    public static Specification<TCorporatura> idCorporaturaEqual(String idCorporatura) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TCorporatura_.idCorporatura), idCorporatura);
    }

    public static Specification<TCorporatura> descrizioneCorporaturaEqual(String descrizioneCorporatura) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TCorporatura_.corporatura), descrizioneCorporatura);
    }

    public static Specification<TCorporatura> dataLessThanDIniVal(LocalDate dataRif) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.lessThanOrEqualTo(root.get(TCorporatura_.dataInizioValidita), dataRif);
    }

    public static Specification<TCorporatura> dataRifGreaterThanDFinVal(LocalDate dataRif) {

        return (root, query, criteriaBuilder) -> {
            LocalDate ld = LocalDate.parse("9999-12-31") ;
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.coalesce(root.get(TCorporatura_.dataFineValidita), ld),
                    dataRif);
        };
    }

}
