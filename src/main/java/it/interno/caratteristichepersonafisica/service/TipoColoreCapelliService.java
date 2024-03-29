package it.interno.caratteristichepersonafisica.service;

import it.interno.caratteristichepersonafisica.dto.TColoreCapelliDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;

import java.time.LocalDate;
import java.util.List;

public interface TipoColoreCapelliService {

    List<TColoreCapelliDto> getTipoColoreCapelliByDataRif(LocalDate dataRiferimento) ;
    TColoreCapelliDto findByKeyAndDescrAndData(String idColoreCapelli, String descColoreCapelli, LocalDate dataRiferimento) throws NoDataException ;
    TColoreCapelliDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException;
    TColoreCapelliDto getColoreCapelliByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException ;
}
