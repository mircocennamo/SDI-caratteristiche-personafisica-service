package it.interno.caratteristichepersonafisica.repository.specifications;

import it.interno.caratteristichepersonafisica.entity.TAndatura;
import it.interno.caratteristichepersonafisica.entity.TAndatura_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TAndaturaSpecifications{

    public static Specification<TAndatura> idAndaturaEqual(String idAndatura) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TAndatura_.idAndatura), idAndatura);
    }

    public static Specification<TAndatura> descrizioneAndaturaEqual(String descrizioneAndatura) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TAndatura_.andatura), descrizioneAndatura);
    }

    public static Specification<TAndatura> dataLessThanDIniVal(LocalDate dataRif) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.lessThanOrEqualTo(root.get(TAndatura_.dataInizioValidita), dataRif);
    }

    public static Specification<TAndatura> dataRifGreaterThanDFinVal(LocalDate dataRif) {

        return (root, query, criteriaBuilder) -> {
            LocalDate ld = LocalDate.parse("9999-12-31") ;
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.coalesce(root.get(TAndatura_.dataFineValidita), ld),
                    dataRif);
        };
    }

}
