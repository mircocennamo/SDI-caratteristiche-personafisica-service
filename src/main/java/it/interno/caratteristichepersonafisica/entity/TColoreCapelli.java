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
@Table(name = "TCOLORECAPELLI", schema = "DBCARATPF")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TColoreCapelli implements Serializable {

    static final long serialVersionUID = 7765231800506594716L;
    @Id
    @Column(name = "IDCOLORECAPELLI", nullable = false, length = 4, columnDefinition = "CHAR")
    String idColoreCapelli;

    @Column(name = "COLORECAPELLI", nullable = false, length = 100)
    String coloreCapelli;

    @Column(name = "DATAINIZIOVALIDITA", nullable = false)
    LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita;

}
