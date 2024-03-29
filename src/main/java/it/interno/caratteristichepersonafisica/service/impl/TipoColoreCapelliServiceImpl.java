package it.interno.caratteristichepersonafisica.service.impl;

import it.interno.caratteristichepersonafisica.dto.TColoreCapelliDto;
import it.interno.caratteristichepersonafisica.entity.TColoreCapelli;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.mapper.TColoreCapelliMapper;
import it.interno.caratteristichepersonafisica.repository.TColoreCapelliRepository;
import it.interno.caratteristichepersonafisica.repository.specifications.TColoreCapelliSpecifications;
import it.interno.caratteristichepersonafisica.service.TipoColoreCapelliService;
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

import static it.interno.caratteristichepersonafisica.repository.specifications.TColoreCapelliSpecifications.*;

@Service
@Transactional(readOnly = true)
public class TipoColoreCapelliServiceImpl implements TipoColoreCapelliService {

    @Autowired
    TColoreCapelliRepository tColoreCapelliRepository ;

    @Autowired
    TColoreCapelliMapper tColoreCapelliMapper ;

    @Override
    public List<TColoreCapelliDto> getTipoColoreCapelliByDataRif(LocalDate dataRiferimento) {
        List<TColoreCapelliDto> tColoreCapelliDtoList = new ArrayList<>() ;
        List<TColoreCapelli> tColoreCapelliList = tColoreCapelliRepository.findAll(
                dataLessThanDIniVal(dataRiferimento)
                        .and(dataRifGreaterThanDFinVal(dataRiferimento)),
                Sort.by(Sort.Direction.ASC, "coloreCapelli")) ;
        if(!CollectionUtils.isEmpty(tColoreCapelliList)) {
            tColoreCapelliList.stream().forEach(tColoreCapelli -> {
                tColoreCapelliDtoList.add(tColoreCapelliMapper.tColoreCapelliToTColoreCapelliDto(tColoreCapelli)) ;
            });
        }

        return tColoreCapelliDtoList;
    }

    @Override
    public TColoreCapelliDto findByKeyAndDescrAndData(String idColoreCapelli, String descColoreCapelli, LocalDate dataRiferimento) throws NoDataException {
        Optional<TColoreCapelli> optionalTColoreCapelli = tColoreCapelliRepository.findOne(
                idColoreCapelliEqual(idColoreCapelli)
                        .and(descrizioneColoreCapelliEqual(descColoreCapelli))
                        .and(TColoreCapelliSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TColoreCapelliSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TColoreCapelli tColoreCapelli = optionalTColoreCapelli.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Colore Capelli"));

        return tColoreCapelliMapper.tColoreCapelliToTColoreCapelliDto(tColoreCapelli);
    }

    @Override
    public TColoreCapelliDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException {
        Optional<TColoreCapelli> optionalTColoreCapelli = tColoreCapelliRepository.findOne(
                idColoreCapelliEqual(id)
                        .and(TColoreCapelliSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TColoreCapelliSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TColoreCapelli tColoreCapelli = optionalTColoreCapelli.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Colore Capelli"));

        return tColoreCapelliMapper.tColoreCapelliToTColoreCapelliDto(tColoreCapelli);
    }

    @Override
    public TColoreCapelliDto getColoreCapelliByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException {

        return StringUtils.isBlank(descrizione)
                ? this.findByIdAndData(id, dataRif)
                : this.findByKeyAndDescrAndData(id, descrizione, dataRif);
    }
}