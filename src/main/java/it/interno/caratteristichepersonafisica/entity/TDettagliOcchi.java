package it.interno.caratteristichepersonafisica.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TDETTAGLIOCCHI", schema = "DBCARATPF")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TDettagliOcchi implements Serializable {
    
    static final long serialVersionUID = 1832695088357436129L;
    @Id
    @Column(name = "IDDETTAGLIOCCHI", nullable = false, unique = true, length = 4, columnDefinition = "CHAR")
    String idDettagliOcchi;

    @Column(name = "DETTAGLIOCCHI", nullable = false, length = 100)
    String dettagliOcchi;

    @Column(name = "DATAINIZIOVALIDITA", nullable = false)
    LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita;

}
