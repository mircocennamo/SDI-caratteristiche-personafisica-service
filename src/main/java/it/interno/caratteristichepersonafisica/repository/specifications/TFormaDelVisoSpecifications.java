package it.interno.caratteristichepersonafisica.repository.specifications;

import it.interno.caratteristichepersonafisica.entity.TFormaDelViso;
import it.interno.caratteristichepersonafisica.entity.TFormaDelViso_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TFormaDelVisoSpecifications {

    public static Specification<TFormaDelViso> idFormaDelVisoEqual(String idFormaDelViso) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TFormaDelViso_.idFormaDelViso), idFormaDelViso);
    }

    public static Specification<TFormaDelViso> descrizioneFormaDelVisoEqual(String descrizioneFormaDelViso) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get(TFormaDelViso_.formaDelViso), descrizioneFormaDelViso);
    }

    public static Specification<TFormaDelViso> dataLessThanDIniVal(LocalDate dataRif) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.lessThanOrEqualTo(root.get(TFormaDelViso_.dataInizioValidita), dataRif);
    }

    public static Specification<TFormaDelViso> dataRifGreaterThanDFinVal(LocalDate dataRif) {

        return (root, query, criteriaBuilder) -> {
            LocalDate ld = LocalDate.parse("9999-12-31") ;
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.coalesce(root.get(TFormaDelViso_.dataFineValidita), ld),
                    dataRif);
        };
    }
}
