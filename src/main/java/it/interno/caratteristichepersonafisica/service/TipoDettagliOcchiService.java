package it.interno.caratteristichepersonafisica.service;

import it.interno.caratteristichepersonafisica.dto.TDettagliOcchiDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;

import java.time.LocalDate;
import java.util.List;

public interface TipoDettagliOcchiService {

    List<TDettagliOcchiDto> getTipoDettagliOcchiByDataRif(LocalDate dataRiferimento) ;
    TDettagliOcchiDto findByKeyAndDescrAndData(String idDettagliOcchi, String descDettagliOcchi, LocalDate dataRiferimento) throws NoDataException;
    TDettagliOcchiDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException;
    TDettagliOcchiDto getDettagliOcchiByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException ;
}
