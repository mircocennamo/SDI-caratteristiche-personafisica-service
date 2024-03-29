package it.interno.caratteristichepersonafisica.repository.specifications;

import it.interno.caratteristichepersonafisica.entity.TOrecchie;
import it.interno.caratteristichepersonafisica.entity.TOrecchie_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TOrecchieSpecifications {

    public static Specification<TOrecchie> idOrecchieEqual(String idOrecchie) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TOrecchie_.idOrecchie), idOrecchie);
    }

    public static Specification<TOrecchie> descrizioneOrecchieEqual(String descrizioneOrecchie) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TOrecchie_.orecchie), descrizioneOrecchie);
    }

    public static Specification<TOrecchie> dataLessThanDIniVal(LocalDate dataRif) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.lessThanOrEqualTo(root.get(TOrecchie_.dataInizioValidita), dataRif);
    }

    public static Specification<TOrecchie> dataRifGreaterThanDFinVal(LocalDate dataRif) {

        return (root, query, criteriaBuilder) -> {
            LocalDate ld = LocalDate.parse("9999-12-31") ;
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.coalesce(root.get(TOrecchie_.dataFineValidita), ld),
                    dataRif);
        };
    }

}
