package it.interno.caratteristichepersonafisica.service;

import it.interno.caratteristichepersonafisica.dto.TOrecchieDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;

import java.time.LocalDate;
import java.util.List;

public interface TipoOrecchieService {

    List<TOrecchieDto> getTipoOrecchieByDataRif(LocalDate dataRiferiOrecchie) ;
    TOrecchieDto findByKeyAndDescrAndData(String idOrecchie, String descOrecchie, LocalDate dataRiferimento) throws NoDataException;
    TOrecchieDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException;
    TOrecchieDto getOrecchieByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException ;
}
