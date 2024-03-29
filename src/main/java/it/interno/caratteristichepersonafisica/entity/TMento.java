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
@Table(name = "TMENTO", schema = "DBCARATPF")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TMento implements Serializable {
    
    static final long serialVersionUID = 9079167965613681391L;
    @Id
    @Column(name = "IDMENTO", nullable = false, unique = true, length = 4, columnDefinition = "CHAR")
    String idMento;

    @Column(name = "MENTO", nullable = false, length = 100)
    String mento;

    @Column(name = "DATAINIZIOVALIDITA", nullable = false)
    LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita;

}
