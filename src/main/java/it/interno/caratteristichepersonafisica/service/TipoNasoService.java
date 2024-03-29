package it.interno.caratteristichepersonafisica.service;

import it.interno.caratteristichepersonafisica.dto.TNasoDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;

import java.time.LocalDate;
import java.util.List;

public interface TipoNasoService {

    List<TNasoDto> getTipoNasoByDataRif(LocalDate dataRiferiNaso) ;
    TNasoDto findByKeyAndDescrAndData(String idNaso, String descNaso, LocalDate dataRiferimento) throws NoDataException;
    TNasoDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException;
    TNasoDto getNasoByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException ;
}
