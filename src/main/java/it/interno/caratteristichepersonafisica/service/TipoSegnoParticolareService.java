package it.interno.caratteristichepersonafisica.service;

import it.interno.caratteristichepersonafisica.dto.TSegnoParticolareDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;

import java.time.LocalDate;
import java.util.List;

public interface TipoSegnoParticolareService {

    List<TSegnoParticolareDto> getTipoSegnoParticolareByDataRif(LocalDate dataRiferiSegnoParticolare) ;
    List<TSegnoParticolareDto> getSegnoParticolareByIdAndDescrizioneAndData(List<String> idSegnoParticolare, String descrizione, LocalDate dataRif);
}
