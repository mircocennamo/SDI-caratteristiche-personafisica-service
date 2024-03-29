package it.interno.caratteristichepersonafisica.repository.specifications;

import it.interno.caratteristichepersonafisica.entity.TCarnagione;
import it.interno.caratteristichepersonafisica.entity.TCarnagione_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

public class TCarnagioneSpecifications {

    public static Specification<TCarnagione> idCarnagioneEqual(String idCarnagione) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TCarnagione_.idCarnagione), idCarnagione);
    }

    public static Specification<TCarnagione> descrizioneCarnagioneEqual(String descrizioneCarnagione) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TCarnagione_.carnagione), descrizioneCarnagione);
    }

    public static Specification<TCarnagione> dataLessThanDIniVal(LocalDate dataRif) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.lessThanOrEqualTo(root.get(TCarnagione_.dataInizioValidita), dataRif);
    }

    public static Specification<TCarnagione> dataRifGreaterThanDFinVal(LocalDate dataRif) {

        return (root, query, criteriaBuilder) -> {
            LocalDate ld = LocalDate.parse("9999-12-31") ;
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.coalesce(root.get(TCarnagione_.dataFineValidita), ld),
                    dataRif);
        };
    }

    public static Specification<TCarnagione> idCarnagioneIn(List<String> listaIdCarnagione) {
        return (root, query, criteriaBuilder)-> criteriaBuilder.in(root.get(TCarnagione_.ID_CARNAGIONE)).value(listaIdCarnagione);
    }

}
