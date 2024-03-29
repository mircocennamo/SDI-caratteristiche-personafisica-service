package it.interno.caratteristichepersonafisica.service;

import it.interno.caratteristichepersonafisica.dto.TAndaturaDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;

import java.time.LocalDate;
import java.util.List;

public interface TipoAndaturaService {

    List<TAndaturaDto> getTipoAndaturaByDataRif(LocalDate dataRiferimento) ;
    TAndaturaDto getTipiAndaturaByIdAndDescrizioneAndData(String idAndatura, String descrizioneAndatura, LocalDate dataRif) throws NoDataException;
}
