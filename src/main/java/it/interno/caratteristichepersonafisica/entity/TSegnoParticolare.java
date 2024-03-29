package it.interno.caratteristichepersonafisica.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TSEGNOPARTICOLARE", schema = "DBCARATPF")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TSegnoParticolare implements Serializable {

    static final long serialVersionUID = 6603542015685640380L;
    @Id
    @Column(name = "IDSEGNOPARTICOLARE", nullable = false, unique = true, length = 4, columnDefinition = "CHAR")
    String idSegnoParticolare;

    @Column(name = "SEGNOPARTICOLARE", nullable = false, length = 100)
    String segnoParticolare;

    @Column(name = "DATAINIZIOVALIDITA", nullable = false)
    LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita;

}
