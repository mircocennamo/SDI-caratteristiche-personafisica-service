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
@Table(name = "TSTILECAPELLI", schema = "DBCARATPF")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TStileCapelli implements Serializable {
    static final long serialVersionUID = 4163234331809935112L;
    @Id
    @Column(name = "IDSTILECAPELLI", nullable = false, unique = true, length = 4, columnDefinition = "CHAR")
    String idStileCapelli;

    @Column(name = "STILECAPELLI", nullable = false, length = 100)
    String stileCapelli;

    @Column(name = "DATAINIZIOVALIDITA", nullable = false)
    LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita;

}
