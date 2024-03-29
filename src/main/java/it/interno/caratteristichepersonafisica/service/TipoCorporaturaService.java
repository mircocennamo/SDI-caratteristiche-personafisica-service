package it.interno.caratteristichepersonafisica.service;

import it.interno.caratteristichepersonafisica.dto.TCorporaturaDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;

import java.time.LocalDate;
import java.util.List;

public interface TipoCorporaturaService {

    List<TCorporaturaDto> getTipoCorporaturaByDataRif(LocalDate dataRiferimento) ;
    TCorporaturaDto findByKeyAndDescrAndData(String idCorporatura, String descCorporatura, LocalDate dataRiferimento) throws NoDataException;
    TCorporaturaDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException;
    TCorporaturaDto getCorporaturaByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException ;
}
