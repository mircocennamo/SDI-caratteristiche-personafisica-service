package it.interno.caratteristichepersonafisica.service;

import it.interno.caratteristichepersonafisica.dto.TMentoDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;

import java.time.LocalDate;
import java.util.List;

public interface TipoMentoService {

    List<TMentoDto> getTipoMentoByDataRif(LocalDate dataRiferimento) ;
    TMentoDto findByKeyAndDescrAndData(String idMento, String descMento, LocalDate dataRiferimento) throws NoDataException;
    TMentoDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException;
    TMentoDto getMentoByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException ;
}
