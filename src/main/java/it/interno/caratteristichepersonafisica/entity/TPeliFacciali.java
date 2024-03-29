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
@Table(name = "TPELIFACCIALI", schema = "DBCARATPF")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TPeliFacciali implements Serializable {
    
    static final long serialVersionUID = -5786434111984207965L;
    @Id
    @Column(name = "IDPELIFACCIALI", nullable = false, unique = true, length = 4, columnDefinition = "CHAR")
    String idPeliFacciali;

    @Column(name = "PELIFACCIALI", nullable = false, length = 100)
    String peliFacciali;

    @Column(name = "DATAINIZIOVALIDITA", nullable = false)
    LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita;

}
