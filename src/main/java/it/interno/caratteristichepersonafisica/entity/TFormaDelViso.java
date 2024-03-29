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
@Table(name = "TFORMADELVISO", schema = "DBCARATPF")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TFormaDelViso implements Serializable {
    
    static final long serialVersionUID = -2999890171187551875L;
    @Id
    @Column(name = "IDFORMADELVISO", nullable = false, unique = true, length = 4, columnDefinition = "CHAR")
    String idFormaDelViso;

    @Column(name = "FORMADELVISO", nullable = false, length = 100)
    String formaDelViso;

    @Column(name = "DATAINIZIOVALIDITA", nullable = false)
    LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita;

}
