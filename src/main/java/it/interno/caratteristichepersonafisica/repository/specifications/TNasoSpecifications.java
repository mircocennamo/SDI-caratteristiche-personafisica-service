package it.interno.caratteristichepersonafisica.repository.specifications;

import it.interno.caratteristichepersonafisica.entity.TNaso;
import it.interno.caratteristichepersonafisica.entity.TNaso_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TNasoSpecifications {

    public static Specification<TNaso> idNasoEqual(String idNaso) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TNaso_.idNaso), idNaso);
    }

    public static Specification<TNaso> descrizioneNasoEqual(String descrizioneNaso) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TNaso_.naso), descrizioneNaso);
    }

    public static Specification<TNaso> dataLessThanDIniVal(LocalDate dataRif) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.lessThanOrEqualTo(root.get(TNaso_.dataInizioValidita), dataRif);
    }

    public static Specification<TNaso> dataRifGreaterThanDFinVal(LocalDate dataRif) {

        return (root, query, criteriaBuilder) -> {
            LocalDate ld = LocalDate.parse("9999-12-31") ;
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.coalesce(root.get(TNaso_.dataFineValidita), ld),
                    dataRif);
        };
    }

}
