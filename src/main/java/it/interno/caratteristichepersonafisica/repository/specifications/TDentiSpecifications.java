package it.interno.caratteristichepersonafisica.repository.specifications;

import it.interno.caratteristichepersonafisica.entity.TDenti;
import it.interno.caratteristichepersonafisica.entity.TDenti_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TDentiSpecifications {

    public static Specification<TDenti> idDentiEqual(String idDenti) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TDenti_.idDenti), idDenti);
    }

    public static Specification<TDenti> descrizioneDentiEqual(String descrizioneDenti) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TDenti_.denti), descrizioneDenti);
    }

    public static Specification<TDenti> dataLessThanDIniVal(LocalDate dataRif) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.lessThanOrEqualTo(root.get(TDenti_.dataInizioValidita), dataRif);
    }

    public static Specification<TDenti> dataRifGreaterThanDFinVal(LocalDate dataRif) {

        return (root, query, criteriaBuilder) -> {
            LocalDate ld = LocalDate.parse("9999-12-31") ;
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.coalesce(root.get(TDenti_.dataFineValidita), ld),
                    dataRif);
        };
    }

}
