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
@Table(name = "TCORPORATURA", schema = "DBCARATPF")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TCorporatura implements Serializable {
    
    static final long serialVersionUID = 8044444829829084110L;
    @Id
    @Column(name = "IDCORPORATURA", nullable = false, unique = true, length = 4, columnDefinition = "CHAR")
    String idCorporatura;

    @Column(name = "CORPORATURA", nullable = false, length = 100)
    String corporatura;

    @Column(name = "DATAINIZIOVALIDITA", nullable = false)
    LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita;

}
