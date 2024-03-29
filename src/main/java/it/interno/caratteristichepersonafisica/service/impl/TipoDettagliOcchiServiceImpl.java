package it.interno.caratteristichepersonafisica.service.impl;

import it.interno.caratteristichepersonafisica.dto.TDettagliOcchiDto;
import it.interno.caratteristichepersonafisica.entity.TDettagliOcchi;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.mapper.TDettagliOcchiMapper;
import it.interno.caratteristichepersonafisica.repository.TDettagliOcchiRepository;
import it.interno.caratteristichepersonafisica.repository.specifications.TDettagliOcchiSpecifications;
import it.interno.caratteristichepersonafisica.service.TipoDettagliOcchiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static it.interno.caratteristichepersonafisica.repository.specifications.TDettagliOcchiSpecifications.*;

@Service
@Transactional(readOnly = true)
public class TipoDettagliOcchiServiceImpl implements TipoDettagliOcchiService {

    @Autowired
    TDettagliOcchiRepository tDettagliOcchiRepository ;

    @Autowired
    TDettagliOcchiMapper tDettagliOcchiMapper ;

    @Override
    public List<TDettagliOcchiDto> getTipoDettagliOcchiByDataRif(LocalDate dataRiferimento) {
        List<TDettagliOcchiDto> tDettagliOcchiDtoList = new ArrayList<>() ;
        List<TDettagliOcchi> tDettagliOcchiList = tDettagliOcchiRepository.findAll(
                dataLessThanDIniVal(dataRiferimento)
                        .and(dataRifGreaterThanDFinVal(dataRiferimento)),
                Sort.by(Sort.Direction.ASC, "dettagliOcchi")) ;
        if(!CollectionUtils.isEmpty(tDettagliOcchiList)) {
            tDettagliOcchiList.stream().forEach(tDettagliOcchi -> {
                tDettagliOcchiDtoList.add(tDettagliOcchiMapper.tDettagliOcchiToTDettagliOcchiDto(tDettagliOcchi)) ;
            });
        }

        return tDettagliOcchiDtoList;
    }

    @Override
    public TDettagliOcchiDto findByKeyAndDescrAndData(String idDettagliOcchi, String descDettagliOcchi, LocalDate dataRiferimento) throws NoDataException {
        Optional<TDettagliOcchi> optionalTDettagliOcchi = tDettagliOcchiRepository.findOne(
                idDettagliOcchiEqual(idDettagliOcchi)
                        .and(descrizioneDettagliOcchiEqual(descDettagliOcchi))
                        .and(TDettagliOcchiSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TDettagliOcchiSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TDettagliOcchi tDettagliOcchi = optionalTDettagliOcchi.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Dettagli Occhi"));

        return tDettagliOcchiMapper.tDettagliOcchiToTDettagliOcchiDto(tDettagliOcchi);
    }

    @Override
    public TDettagliOcchiDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException {
        Optional<TDettagliOcchi> optionalTDettagliOcchi = tDettagliOcchiRepository.findOne(
                idDettagliOcchiEqual(id)
                        .and(TDettagliOcchiSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TDettagliOcchiSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TDettagliOcchi tDettagliOcchi = optionalTDettagliOcchi.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Dettagli Occhi"));

        return tDettagliOcchiMapper.tDettagliOcchiToTDettagliOcchiDto(tDettagliOcchi);
    }

    @Override
    public TDettagliOcchiDto getDettagliOcchiByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException {

        return StringUtils.isBlank(descrizione)
                ? this.findByIdAndData(id, dataRif)
                : this.findByKeyAndDescrAndData(id, descrizione, dataRif);
    }
}