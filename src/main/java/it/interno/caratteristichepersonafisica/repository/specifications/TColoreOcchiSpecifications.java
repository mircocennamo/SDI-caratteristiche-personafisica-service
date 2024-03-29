package it.interno.caratteristichepersonafisica.repository.specifications;

import it.interno.caratteristichepersonafisica.entity.TColoreOcchi;
import it.interno.caratteristichepersonafisica.entity.TColoreOcchi_;
import it.interno.caratteristichepersonafisica.entity.TColoreOcchi;
import it.interno.caratteristichepersonafisica.entity.TColoreOcchi_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TColoreOcchiSpecifications {

    public static Specification<TColoreOcchi> idColoreOcchiEqual(String idColoreOcchi) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TColoreOcchi_.idColoreOcchi), idColoreOcchi);
    }

    public static Specification<TColoreOcchi> descrizioneColoreOcchiEqual(String descrizioneColoreOcchi) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TColoreOcchi_.coloreOcchi), descrizioneColoreOcchi);
    }

    public static Specification<TColoreOcchi> dataLessThanDIniVal(LocalDate dataRif) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.lessThanOrEqualTo(root.get(TColoreOcchi_.dataInizioValidita), dataRif);
    }

    public static Specification<TColoreOcchi> dataRifGreaterThanDFinVal(LocalDate dataRif) {

        return (root, query, criteriaBuilder) -> {
            LocalDate ld = LocalDate.parse("9999-12-31") ;
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.coalesce(root.get(TColoreOcchi_.dataFineValidita), ld),
                    dataRif);
        };
    }
}
