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
@Table(name = "TANDATURA", schema = "DBCARATPF")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TAndatura implements Serializable {
    static final long serialVersionUID = -8442922097523384027L;
    @Id
    @Column(name = "IDANDATURA", unique = true, nullable = false, length = 4, columnDefinition = "CHAR")
    String idAndatura;

    @Column(name = "ANDATURA", nullable = false, length = 100)
    String andatura;

    @Column(name = "DATAINIZIOVALIDITA", nullable = false)
    LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita;

}
