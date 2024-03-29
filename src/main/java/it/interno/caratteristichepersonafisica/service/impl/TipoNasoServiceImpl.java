package it.interno.caratteristichepersonafisica.service.impl;

import it.interno.caratteristichepersonafisica.dto.TNasoDto;
import it.interno.caratteristichepersonafisica.entity.TNaso;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.mapper.TNasoMapper;
import it.interno.caratteristichepersonafisica.repository.TNasoRepository;
import it.interno.caratteristichepersonafisica.repository.specifications.TNasoSpecifications;
import it.interno.caratteristichepersonafisica.service.TipoNasoService;
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

import static it.interno.caratteristichepersonafisica.repository.specifications.TNasoSpecifications.*;

@Service
@Transactional(readOnly = true)
public class TipoNasoServiceImpl implements TipoNasoService {

    @Autowired
    TNasoRepository tNasoRepository ;

    @Autowired
    TNasoMapper tNasoMapper ;

    @Override
    public List<TNasoDto> getTipoNasoByDataRif(LocalDate dataRiferimento) {
        List<TNasoDto> tNasoDtoList = new ArrayList<>() ;
        List<TNaso> tNasoList = tNasoRepository.findAll(
                dataLessThanDIniVal(dataRiferimento)
                        .and(dataRifGreaterThanDFinVal(dataRiferimento)),
                Sort.by(Sort.Direction.ASC, "naso")) ;
        if(!CollectionUtils.isEmpty(tNasoList)) {
            tNasoList.stream().forEach(tNaso -> {
                tNasoDtoList.add(tNasoMapper.tNasoToTNasoDto(tNaso)) ;
            });
        }

        return tNasoDtoList;
    }

    @Override
    public TNasoDto findByKeyAndDescrAndData(String idNaso, String descNaso, LocalDate dataRiferimento) throws NoDataException {
        Optional<TNaso> optionalTNaso = tNasoRepository.findOne(
                idNasoEqual(idNaso)
                        .and(descrizioneNasoEqual(descNaso))
                        .and(TNasoSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TNasoSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TNaso tNaso = optionalTNaso.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Naso"));

        return tNasoMapper.tNasoToTNasoDto(tNaso);
    }

    @Override
    public TNasoDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException {
        Optional<TNaso> optionalTNaso = tNasoRepository.findOne(
                idNasoEqual(id)
                        .and(TNasoSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TNasoSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TNaso tNaso = optionalTNaso.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Naso"));

        return tNasoMapper.tNasoToTNasoDto(tNaso);
    }

    @Override
    public TNasoDto getNasoByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException {

        return StringUtils.isBlank(descrizione)
                ? this.findByIdAndData(id, dataRif)
                : this.findByKeyAndDescrAndData(id, descrizione, dataRif);
    }
}