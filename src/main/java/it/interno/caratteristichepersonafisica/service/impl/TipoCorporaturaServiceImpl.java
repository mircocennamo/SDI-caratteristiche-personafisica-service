package it.interno.caratteristichepersonafisica.service.impl;

import it.interno.caratteristichepersonafisica.dto.TCorporaturaDto;
import it.interno.caratteristichepersonafisica.entity.TCorporatura;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.mapper.TCorporaturaMapper;
import it.interno.caratteristichepersonafisica.repository.TCorporaturaRepository;
import it.interno.caratteristichepersonafisica.repository.specifications.TCorporaturaSpecifications;
import it.interno.caratteristichepersonafisica.service.TipoCorporaturaService;
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

import static it.interno.caratteristichepersonafisica.repository.specifications.TCorporaturaSpecifications.*;

@Service
@Transactional(readOnly = true)
public class TipoCorporaturaServiceImpl implements TipoCorporaturaService {

    @Autowired
    TCorporaturaRepository tCorporaturaRepository ;

    @Autowired
    TCorporaturaMapper tCorporaturaMapper ;

    @Override
    public List<TCorporaturaDto> getTipoCorporaturaByDataRif(LocalDate dataRiferimento) {
        List<TCorporaturaDto> tCorporaturaDtoList = new ArrayList<>() ;
        List<TCorporatura> tCorporaturaList = tCorporaturaRepository.findAll(
                dataLessThanDIniVal(dataRiferimento)
                        .and(dataRifGreaterThanDFinVal(dataRiferimento)),
                Sort.by(Sort.Direction.ASC, "corporatura")) ;
        if(!CollectionUtils.isEmpty(tCorporaturaList)) {
            tCorporaturaList.stream().forEach(tCorporatura -> {
                tCorporaturaDtoList.add(tCorporaturaMapper.tCorporaturaToTCorporaturaDto(tCorporatura)) ;
            });
        }

        return tCorporaturaDtoList;
    }

    @Override
    public TCorporaturaDto findByKeyAndDescrAndData(String idCorporatura, String descCorporatura, LocalDate dataRiferimento) throws NoDataException {
        Optional<TCorporatura> optionalTCorporatura = tCorporaturaRepository.findOne(
                idCorporaturaEqual(idCorporatura)
                        .and(descrizioneCorporaturaEqual(descCorporatura))
                        .and(TCorporaturaSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TCorporaturaSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TCorporatura tCorporatura = optionalTCorporatura.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Corporatura"));

        return tCorporaturaMapper.tCorporaturaToTCorporaturaDto(tCorporatura);
    }

    @Override
    public TCorporaturaDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException {
        Optional<TCorporatura> optionalTCorporatura = tCorporaturaRepository.findOne(
                idCorporaturaEqual(id)
                        .and(TCorporaturaSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TCorporaturaSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TCorporatura tCorporatura = optionalTCorporatura.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Corporatura"));

        return tCorporaturaMapper.tCorporaturaToTCorporaturaDto(tCorporatura);
    }

    @Override
    public TCorporaturaDto getCorporaturaByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException {

        return StringUtils.isBlank(descrizione)
                ? this.findByIdAndData(id, dataRif)
                : this.findByKeyAndDescrAndData(id, descrizione, dataRif);
    }
}