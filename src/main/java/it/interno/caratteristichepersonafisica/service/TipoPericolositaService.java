package it.interno.caratteristichepersonafisica.service;

import it.interno.caratteristichepersonafisica.dto.TPericolositaDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;

import java.time.LocalDate;
import java.util.List;

public interface TipoPericolositaService {

    List<TPericolositaDto> getTipoPericolositaByDataRif(LocalDate dataRiferiPericolosita) ;
    List<TPericolositaDto> getPericolositaByIdAndDescrizioneAndData(List<String> idPericolosita, String descrizione, LocalDate dataRiferimento) ;
}
