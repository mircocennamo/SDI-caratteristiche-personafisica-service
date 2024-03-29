package it.interno.caratteristichepersonafisica.service;

import it.interno.caratteristichepersonafisica.dto.TFormaDelVisoDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;

import java.time.LocalDate;
import java.util.List;

public interface TipoFormaDelVisoService {

    List<TFormaDelVisoDto> getTipoFormaDelVisoByDataRif(LocalDate dataRiferimento) ;
    TFormaDelVisoDto findByKeyAndDescrAndData(String idFormaDelViso, String descFormaDelViso, LocalDate dataRiferimento) throws NoDataException;
    TFormaDelVisoDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException;
    TFormaDelVisoDto getFormaDelVisoByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException ;
}
