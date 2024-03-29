package it.interno.caratteristichepersonafisica.service;

import it.interno.caratteristichepersonafisica.dto.TStileCapelliDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;

import java.time.LocalDate;
import java.util.List;

public interface TipoStileCapelliService {

    List<TStileCapelliDto> getTipoStileCapelliByDataRif(LocalDate dataRiferiStileCapelli) ;
    TStileCapelliDto findByKeyAndDescrAndData(String idStileCapelli, String descStileCapelli, LocalDate dataRiferimento) throws NoDataException;
    TStileCapelliDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException;
    TStileCapelliDto getStileCapelliByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException ;
}
