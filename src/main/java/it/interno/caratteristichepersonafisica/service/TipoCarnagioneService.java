package it.interno.caratteristichepersonafisica.service;

import it.interno.caratteristichepersonafisica.dto.TCarnagioneDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;

import java.time.LocalDate;
import java.util.List;

public interface TipoCarnagioneService {

    List<TCarnagioneDto> getTipoCarnagioneByDataRif(LocalDate dataRiferimento) ;
    List<TCarnagioneDto> getCarnagioneByIdAndDescrizioneAndData(List<String> idCarnagione, String descrizione, LocalDate dataRif) ;
}
