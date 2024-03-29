package it.interno.caratteristichepersonafisica.service;

import it.interno.caratteristichepersonafisica.dto.TPeliFaccialiDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;

import java.time.LocalDate;
import java.util.List;

public interface TipoPeliFaccialiService {

    List<TPeliFaccialiDto> getTipoPeliFaccialiByDataRif(LocalDate dataRiferiPeliFacciali) ;
    TPeliFaccialiDto findByKeyAndDescrAndData(String idPeliFacciali, String descPeliFacciali, LocalDate dataRiferimento) throws NoDataException;
    TPeliFaccialiDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException;
    TPeliFaccialiDto getPeliFaccialiByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException ;
}
