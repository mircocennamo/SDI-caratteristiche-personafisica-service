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
@Table(name = "TORECCHIE", schema = "DBCARATPF")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TOrecchie implements Serializable {
    
    static final long serialVersionUID = 7179688654542357324L;
    @Id
    @Column(name = "IDORECCHIE", nullable = false, unique = true, length = 4, columnDefinition = "CHAR")
    String idOrecchie;

    @Column(name = "ORECCHIE", nullable = false, length = 100)
    String orecchie;

    @Column(name = "DATAINIZIOVALIDITA", nullable = false)
    LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita;

}
