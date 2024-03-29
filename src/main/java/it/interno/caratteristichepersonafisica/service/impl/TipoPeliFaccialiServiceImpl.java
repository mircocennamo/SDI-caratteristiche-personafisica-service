package it.interno.caratteristichepersonafisica.service.impl;

import it.interno.caratteristichepersonafisica.dto.TPeliFaccialiDto;
import it.interno.caratteristichepersonafisica.entity.TPeliFacciali;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.mapper.TPeliFaccialiMapper;
import it.interno.caratteristichepersonafisica.repository.TPeliFaccialiRepository;
import it.interno.caratteristichepersonafisica.repository.specifications.TPeliFaccialiSpecifications;
import it.interno.caratteristichepersonafisica.service.TipoPeliFaccialiService;
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

import static it.interno.caratteristichepersonafisica.repository.specifications.TPeliFaccialiSpecifications.*;

@Service
@Transactional(readOnly = true)
public class TipoPeliFaccialiServiceImpl implements TipoPeliFaccialiService {

    @Autowired
    TPeliFaccialiRepository tPeliFaccialiRepository ;

    @Autowired
    TPeliFaccialiMapper tPeliFaccialiMapper ;

    @Override
    public List<TPeliFaccialiDto> getTipoPeliFaccialiByDataRif(LocalDate dataRiferiPeliFacciali) {
        List<TPeliFaccialiDto> tPeliFaccialiDtoList = new ArrayList<>() ;
        List<TPeliFacciali> tPeliFaccialiList = tPeliFaccialiRepository.findAll(
                dataLessThanDIniVal(dataRiferiPeliFacciali)
                        .and(dataRifGreaterThanDFinVal(dataRiferiPeliFacciali)),
                Sort.by(Sort.Direction.ASC, "peliFacciali")) ;
        if(!CollectionUtils.isEmpty(tPeliFaccialiList)) {
            tPeliFaccialiList.stream().forEach(tPeliFacciali -> {
                tPeliFaccialiDtoList.add(tPeliFaccialiMapper.tPeliFaccialiToTPeliFaccialiDto(tPeliFacciali)) ;
            });
        }

        return tPeliFaccialiDtoList;
    }

    @Override
    public TPeliFaccialiDto findByKeyAndDescrAndData(String idPeliFacciali, String descPeliFacciali, LocalDate dataRiferimento) throws NoDataException {
        Optional<TPeliFacciali> optionalTPeliFacciali = tPeliFaccialiRepository.findOne(
                idPeliFaccialiEqual(idPeliFacciali)
                        .and(descrizionePeliFaccialiEqual(descPeliFacciali))
                        .and(TPeliFaccialiSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TPeliFaccialiSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TPeliFacciali tPeliFacciali = optionalTPeliFacciali.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Peli Facciali"));

        return tPeliFaccialiMapper.tPeliFaccialiToTPeliFaccialiDto(tPeliFacciali);
    }

    @Override
    public TPeliFaccialiDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException {
        Optional<TPeliFacciali> optionalTPeliFacciali = tPeliFaccialiRepository.findOne(
                idPeliFaccialiEqual(id)
                        .and(TPeliFaccialiSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TPeliFaccialiSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TPeliFacciali tPeliFacciali = optionalTPeliFacciali.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Peli Facciali"));

        return tPeliFaccialiMapper.tPeliFaccialiToTPeliFaccialiDto(tPeliFacciali);
    }

    @Override
    public TPeliFaccialiDto getPeliFaccialiByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException {

        return StringUtils.isBlank(descrizione)
                ? this.findByIdAndData(id, dataRif)
                : this.findByKeyAndDescrAndData(id, descrizione, dataRif);
    }
}