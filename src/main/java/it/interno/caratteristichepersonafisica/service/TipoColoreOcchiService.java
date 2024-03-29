package it.interno.caratteristichepersonafisica.service;

import it.interno.caratteristichepersonafisica.dto.TColoreOcchiDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;

import java.time.LocalDate;
import java.util.List;

public interface TipoColoreOcchiService {

    List<TColoreOcchiDto> getTipoColoreOcchiByDataRif(LocalDate dataRiferimento) ;
    TColoreOcchiDto findByKeyAndDescrAndData(String idColoreOcchi, String descColoreOcchi, LocalDate dataRiferimento) throws NoDataException;
    TColoreOcchiDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException;
    TColoreOcchiDto getColoreOcchiByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException ;
}
