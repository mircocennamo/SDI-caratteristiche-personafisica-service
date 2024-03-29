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
@Table(name = "TNASO", schema = "DBCARATPF")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TNaso implements Serializable {

    static final long serialVersionUID = 5662543364399412477L;
    @Id
    @Column(name = "IDNASO", nullable = false, unique = true, length = 4, columnDefinition = "CHAR")
    String idNaso;

    @Column(name = "NASO", nullable = false, length = 100)
    String naso;

    @Column(name = "DATAINIZIOVALIDITA", nullable = false)
    LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita;

}
