package it.interno.caratteristichepersonafisica.service.impl;

import it.interno.caratteristichepersonafisica.dto.TOrecchieDto;
import it.interno.caratteristichepersonafisica.entity.TOrecchie;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.mapper.TOrecchieMapper;
import it.interno.caratteristichepersonafisica.repository.TOrecchieRepository;
import it.interno.caratteristichepersonafisica.repository.specifications.TOrecchieSpecifications;
import it.interno.caratteristichepersonafisica.service.TipoOrecchieService;
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

import static it.interno.caratteristichepersonafisica.repository.specifications.TOrecchieSpecifications.*;

@Service
@Transactional(readOnly = true)
public class TipoOrecchieServiceImpl implements TipoOrecchieService {

    @Autowired
    TOrecchieRepository tOrecchieRepository ;

    @Autowired
    TOrecchieMapper tOrecchieMapper ;

    @Override
    public List<TOrecchieDto> getTipoOrecchieByDataRif(LocalDate dataRiferimento) {
        List<TOrecchieDto> tOrecchieDtoList = new ArrayList<>() ;
        List<TOrecchie> tOrecchieList = tOrecchieRepository.findAll(
                dataLessThanDIniVal(dataRiferimento)
                        .and(dataRifGreaterThanDFinVal(dataRiferimento)),
                Sort.by(Sort.Direction.ASC, "orecchie")) ;
        if(!CollectionUtils.isEmpty(tOrecchieList)) {
            tOrecchieList.stream().forEach(tOrecchie -> {
                tOrecchieDtoList.add(tOrecchieMapper.tOrecchieToTOrecchieDto(tOrecchie)) ;
            });
        }

        return tOrecchieDtoList;
    }

    @Override
    public TOrecchieDto findByKeyAndDescrAndData(String idOrecchie, String descOrecchie, LocalDate dataRiferimento) throws NoDataException {
        Optional<TOrecchie> optionalTOrecchie = tOrecchieRepository.findOne(
                idOrecchieEqual(idOrecchie)
                        .and(descrizioneOrecchieEqual(descOrecchie))
                        .and(TOrecchieSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TOrecchieSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TOrecchie tOrecchie = optionalTOrecchie.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Orecchie"));

        return tOrecchieMapper.tOrecchieToTOrecchieDto(tOrecchie);
    }

    @Override
    public TOrecchieDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException {
        Optional<TOrecchie> optionalTOrecchie = tOrecchieRepository.findOne(
                idOrecchieEqual(id)
                        .and(TOrecchieSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TOrecchieSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TOrecchie tOrecchie = optionalTOrecchie.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Orecchie"));

        return tOrecchieMapper.tOrecchieToTOrecchieDto(tOrecchie);
    }

    @Override
    public TOrecchieDto getOrecchieByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException {

        return StringUtils.isBlank(descrizione)
                ? this.findByIdAndData(id, dataRif)
                : this.findByKeyAndDescrAndData(id, descrizione, dataRif);
    }
}