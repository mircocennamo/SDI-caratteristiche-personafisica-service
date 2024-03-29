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
@Table(name = "TCOLOREOCCHI", schema = "DBCARATPF")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TColoreOcchi implements Serializable {


    static final long serialVersionUID = 5215610636931658253L;
    @Id
    @Column(name = "IDCOLOREOCCHI", nullable = false, unique = true, length = 4, columnDefinition = "CHAR")
    String idColoreOcchi;

    @Column(name = "COLOREOCCHI", nullable = false, length = 100)
    String coloreOcchi;

    @Column(name = "DATAINIZIOVALIDITA", nullable = false)
    LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita;

}
