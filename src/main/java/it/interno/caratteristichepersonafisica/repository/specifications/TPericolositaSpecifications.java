package it.interno.caratteristichepersonafisica.repository.specifications;

import it.interno.caratteristichepersonafisica.entity.TPericolosita;
import it.interno.caratteristichepersonafisica.entity.TPericolosita_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

public class TPericolositaSpecifications {

    public static Specification<TPericolosita> idPericolositaEqual(String idPericolosita) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TPericolosita_.idPericolosita), idPericolosita);
    }

    public static Specification<TPericolosita> descrizionePericolositaEqual(String descrizionePericolosita) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TPericolosita_.pericolosita), descrizionePericolosita);
    }

    public static Specification<TPericolosita> dataLessThanDIniVal(LocalDate dataRif) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.lessThanOrEqualTo(root.get(TPericolosita_.dataInizioValidita), dataRif);
    }

    public static Specification<TPericolosita> dataRifGreaterThanDFinVal(LocalDate dataRif) {

        return (root, query, criteriaBuilder) -> {
            LocalDate ld = LocalDate.parse("9999-12-31") ;
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.coalesce(root.get(TPericolosita_.dataFineValidita), ld),
                    dataRif);
        };
    }

    public static Specification<TPericolosita> idPericolositaIn(List<String> idPericolosita) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.in(root.get(TPericolosita_.ID_PERICOLOSITA)).value(idPericolosita);
    }

}
