package it.interno.caratteristichepersonafisica.repository.specifications;

import it.interno.caratteristichepersonafisica.entity.TSegnoParticolare;
import it.interno.caratteristichepersonafisica.entity.TSegnoParticolare_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

public class TSegnoParticolareSpecifications {

    public static Specification<TSegnoParticolare> idSegnoParticolareEqual(String idSegnoParticolare) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TSegnoParticolare_.idSegnoParticolare), idSegnoParticolare);
    }

    public static Specification<TSegnoParticolare> descrizioneSegnoParticolareEqual(String descrizioneSegnoParticolare) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TSegnoParticolare_.segnoParticolare), descrizioneSegnoParticolare);
    }

    public static Specification<TSegnoParticolare> dataLessThanDIniVal(LocalDate dataRif) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.lessThanOrEqualTo(root.get(TSegnoParticolare_.dataInizioValidita), dataRif);
    }

    public static Specification<TSegnoParticolare> dataRifGreaterThanDFinVal(LocalDate dataRif) {

        return (root, query, criteriaBuilder) -> {
            LocalDate ld = LocalDate.parse("9999-12-31") ;
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.coalesce(root.get(TSegnoParticolare_.dataFineValidita), ld),
                    dataRif);
        };
    }

    public static Specification<TSegnoParticolare> segnoParticolareIn(List<String> listaSegnoParticolare) {
        return (root, query, criteriaBuilder)-> criteriaBuilder.in(root.get(TSegnoParticolare_.ID_SEGNO_PARTICOLARE)).value(listaSegnoParticolare);
    }

}
