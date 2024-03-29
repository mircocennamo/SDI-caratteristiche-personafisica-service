package it.interno.caratteristichepersonafisica.service.impl;

import it.interno.caratteristichepersonafisica.dto.TMentoDto;
import it.interno.caratteristichepersonafisica.entity.TMento;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.mapper.TMentoMapper;
import it.interno.caratteristichepersonafisica.repository.TMentoRepository;
import it.interno.caratteristichepersonafisica.repository.specifications.TMentoSpecifications;
import it.interno.caratteristichepersonafisica.service.TipoMentoService;
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

import static it.interno.caratteristichepersonafisica.repository.specifications.TMentoSpecifications.*;

@Service
@Transactional(readOnly = true)
public class TipoMentoServiceImpl implements TipoMentoService {

    @Autowired
    TMentoRepository tMentoRepository ;

    @Autowired
    TMentoMapper tMentoMapper ;

    @Override
    public List<TMentoDto> getTipoMentoByDataRif(LocalDate dataRiferimento) {
        List<TMentoDto> tMentoDtoList = new ArrayList<>() ;
        List<TMento> tMentoList = tMentoRepository.findAll(
                dataLessThanDIniVal(dataRiferimento)
                        .and(dataRifGreaterThanDFinVal(dataRiferimento)),
                Sort.by(Sort.Direction.ASC, "mento")) ;
        if(!CollectionUtils.isEmpty(tMentoList)) {
            tMentoList.stream().forEach(tMento -> {
                tMentoDtoList.add(tMentoMapper.tMentoToTMentoDto(tMento)) ;
            });
        }

        return tMentoDtoList;
    }

    @Override
    public TMentoDto findByKeyAndDescrAndData(String idMento, String descMento, LocalDate dataRiferimento) throws NoDataException {
        Optional<TMento> optionalTMento = tMentoRepository.findOne(
                idMentoEqual(idMento)
                        .and(descrizioneMentoEqual(descMento))
                        .and(TMentoSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TMentoSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TMento tMento = optionalTMento.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Mento"));

        return tMentoMapper.tMentoToTMentoDto(tMento);
    }

    @Override
    public TMentoDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException {
        Optional<TMento> optionalTMento = tMentoRepository.findOne(
                idMentoEqual(id)
                        .and(TMentoSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TMentoSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TMento tMento = optionalTMento.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Mento"));

        return tMentoMapper.tMentoToTMentoDto(tMento);
    }

    @Override
    public TMentoDto getMentoByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException {

        return StringUtils.isBlank(descrizione)
                ? this.findByIdAndData(id, dataRif)
                : this.findByKeyAndDescrAndData(id, descrizione, dataRif);
    }
}