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
@Table(name = "TPERICOLOSITA", schema = "DBCARATPF")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TPericolosita implements Serializable {
    
    static final long serialVersionUID = -6758040270361085336L;
    @Id
    @Column(name = "IDPERICOLOSITA", nullable = false, unique = true, length = 4, columnDefinition = "CHAR")
    String idPericolosita;

    @Column(name = "PERICOLOSITA", nullable = false, length = 100)
    String pericolosita;

    @Column(name = "DATAINIZIOVALIDITA", nullable = false)
    LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita;

}
