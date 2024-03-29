package it.interno.caratteristichepersonafisica.repository.specifications;

import it.interno.caratteristichepersonafisica.entity.TPeliFacciali;
import it.interno.caratteristichepersonafisica.entity.TPeliFacciali_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TPeliFaccialiSpecifications {

    public static Specification<TPeliFacciali> idPeliFaccialiEqual(String idPeliFacciali) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TPeliFacciali_.idPeliFacciali), idPeliFacciali);
    }

    public static Specification<TPeliFacciali> descrizionePeliFaccialiEqual(String descrizionePeliFacciali) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TPeliFacciali_.peliFacciali), descrizionePeliFacciali);
    }

    public static Specification<TPeliFacciali> dataLessThanDIniVal(LocalDate dataRif) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.lessThanOrEqualTo(root.get(TPeliFacciali_.dataInizioValidita), dataRif);
    }

    public static Specification<TPeliFacciali> dataRifGreaterThanDFinVal(LocalDate dataRif) {

        return (root, query, criteriaBuilder) -> {
            LocalDate ld = LocalDate.parse("9999-12-31") ;
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.coalesce(root.get(TPeliFacciali_.dataFineValidita), ld),
                    dataRif);
        };
    }

}
