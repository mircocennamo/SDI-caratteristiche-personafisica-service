package it.interno.caratteristichepersonafisica.service;

import it.interno.caratteristichepersonafisica.dto.TDentiDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;

import java.time.LocalDate;
import java.util.List;

public interface TipoDentiService {

    List<TDentiDto> getTipoDentiByDataRif(LocalDate dataRiferimento) ;
    TDentiDto findByKeyAndDescrAndData(String idDenti, String descDenti, LocalDate dataRiferimento) throws NoDataException;
    TDentiDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException;
    TDentiDto getDentiByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException ;
}
