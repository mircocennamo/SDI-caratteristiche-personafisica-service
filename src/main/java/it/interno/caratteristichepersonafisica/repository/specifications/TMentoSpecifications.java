package it.interno.caratteristichepersonafisica.repository.specifications;

import it.interno.caratteristichepersonafisica.entity.TMento;
import it.interno.caratteristichepersonafisica.entity.TMento_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TMentoSpecifications {

    public static Specification<TMento> idMentoEqual(String idMento) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TMento_.idMento), idMento);
    }

    public static Specification<TMento> descrizioneMentoEqual(String descrizioneMento) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TMento_.mento), descrizioneMento);
    }

    public static Specification<TMento> dataLessThanDIniVal(LocalDate dataRif) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.lessThanOrEqualTo(root.get(TMento_.dataInizioValidita), dataRif);
    }

    public static Specification<TMento> dataRifGreaterThanDFinVal(LocalDate dataRif) {

        return (root, query, criteriaBuilder) -> {
            LocalDate ld = LocalDate.parse("9999-12-31") ;
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.coalesce(root.get(TMento_.dataFineValidita), ld),
                    dataRif);
        };
    }

}
