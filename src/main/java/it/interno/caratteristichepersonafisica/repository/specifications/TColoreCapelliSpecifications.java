package it.interno.caratteristichepersonafisica.repository.specifications;

import it.interno.caratteristichepersonafisica.entity.TColoreCapelli;
import it.interno.caratteristichepersonafisica.entity.TColoreCapelli_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TColoreCapelliSpecifications {

    public static Specification<TColoreCapelli> idColoreCapelliEqual(String idColoreCapelli) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TColoreCapelli_.idColoreCapelli), idColoreCapelli);
    }

    public static Specification<TColoreCapelli> descrizioneColoreCapelliEqual(String descrizioneColoreCapelli) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TColoreCapelli_.coloreCapelli), descrizioneColoreCapelli);
    }

    public static Specification<TColoreCapelli> dataLessThanDIniVal(LocalDate dataRif) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.lessThanOrEqualTo(root.get(TColoreCapelli_.dataInizioValidita), dataRif);
    }

    public static Specification<TColoreCapelli> dataRifGreaterThanDFinVal(LocalDate dataRif) {

        return (root, query, criteriaBuilder) -> {
            LocalDate ld = LocalDate.parse("9999-12-31") ;
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.coalesce(root.get(TColoreCapelli_.dataFineValidita), ld),
                    dataRif);
        };
    }
}
