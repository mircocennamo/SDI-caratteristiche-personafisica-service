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
@Table(name = "TCARNAGIONE", schema = "DBCARATPF")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TCarnagione implements Serializable {
    static final long serialVersionUID = -2245016798160579444L;
    @Id
    @Column(name = "IDCARNAGIONE", unique = true, nullable = false, length = 4, columnDefinition = "CHAR")
    String idCarnagione;

    @Column(name = "CARNAGIONE", nullable = false, length = 100)
    String carnagione;

    @Column(name = "DATAINIZIOVALIDITA", nullable = false)
    LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita;

}
