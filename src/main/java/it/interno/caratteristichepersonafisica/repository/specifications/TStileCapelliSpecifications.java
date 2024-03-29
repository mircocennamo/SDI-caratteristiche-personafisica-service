package it.interno.caratteristichepersonafisica.repository.specifications;

import it.interno.caratteristichepersonafisica.entity.TStileCapelli;
import it.interno.caratteristichepersonafisica.entity.TStileCapelli_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TStileCapelliSpecifications {

    public static Specification<TStileCapelli> idStileCapelliEqual(String idStileCapelli) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TStileCapelli_.idStileCapelli), idStileCapelli);
    }

    public static Specification<TStileCapelli> descrizioneStileCapelliEqual(String descrizioneStileCapelli) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TStileCapelli_.stileCapelli), descrizioneStileCapelli);
    }

    public static Specification<TStileCapelli> dataLessThanDIniVal(LocalDate dataRif) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.lessThanOrEqualTo(root.get(TStileCapelli_.dataInizioValidita), dataRif);
    }

    public static Specification<TStileCapelli> dataRifGreaterThanDFinVal(LocalDate dataRif) {

        return (root, query, criteriaBuilder) -> {
            LocalDate ld = LocalDate.parse("9999-12-31") ;
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.coalesce(root.get(TStileCapelli_.dataFineValidita), ld),
                    dataRif);
        };
    }

}
