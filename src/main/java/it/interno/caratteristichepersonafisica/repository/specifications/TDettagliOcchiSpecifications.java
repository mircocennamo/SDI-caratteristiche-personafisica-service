package it.interno.caratteristichepersonafisica.repository.specifications;

import it.interno.caratteristichepersonafisica.entity.TDettagliOcchi;
import it.interno.caratteristichepersonafisica.entity.TDettagliOcchi_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TDettagliOcchiSpecifications {

    public static Specification<TDettagliOcchi> idDettagliOcchiEqual(String idDettagliOcchi) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TDettagliOcchi_.idDettagliOcchi), idDettagliOcchi);
    }

    public static Specification<TDettagliOcchi> descrizioneDettagliOcchiEqual(String descrizioneDettagliOcchi) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TDettagliOcchi_.dettagliOcchi), descrizioneDettagliOcchi);
    }

    public static Specification<TDettagliOcchi> dataLessThanDIniVal(LocalDate dataRif) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.lessThanOrEqualTo(root.get(TDettagliOcchi_.dataInizioValidita), dataRif);
    }

    public static Specification<TDettagliOcchi> dataRifGreaterThanDFinVal(LocalDate dataRif) {

        return (root, query, criteriaBuilder) -> {
            LocalDate ld = LocalDate.parse("9999-12-31") ;
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.coalesce(root.get(TDettagliOcchi_.dataFineValidita), ld),
                    dataRif);
        };
    }
}
