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
@Table(name = "TRELAZIONEPARENTELA", schema = "DBCARATPF")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TRelazioneParentela implements Serializable {
    
    static final long serialVersionUID = -8348664008803392258L;
    @Id
    @Column(name = "IDRELAZIONEPARENTELA", nullable = false, unique = true, length = 4, columnDefinition = "CHAR")
    String idRelazioneParentela;

    @Column(name = "RELAZIONEPARENTELA", nullable = false, length = 100)
    String relazioneParentela;

    @Column(name = "DATAINIZIOVALIDITA", nullable = false)
    LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita;

}
