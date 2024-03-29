package it.interno.caratteristichepersonafisica.service.impl;

import it.interno.caratteristichepersonafisica.dto.TStileCapelliDto;
import it.interno.caratteristichepersonafisica.entity.TStileCapelli;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.mapper.TStileCapelliMapper;
import it.interno.caratteristichepersonafisica.repository.TStileCapelliRepository;
import it.interno.caratteristichepersonafisica.repository.specifications.TStileCapelliSpecifications;
import it.interno.caratteristichepersonafisica.service.TipoStileCapelliService;
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

import static it.interno.caratteristichepersonafisica.repository.specifications.TStileCapelliSpecifications.*;

@Service
@Transactional(readOnly = true)
public class TipoStileCapelliServiceImpl implements TipoStileCapelliService {

    @Autowired
    TStileCapelliRepository tStileCapelliRepository ;

    @Autowired
    TStileCapelliMapper tStileCapelliMapper ;

    @Override
    public List<TStileCapelliDto> getTipoStileCapelliByDataRif(LocalDate dataRiferiStileCapelli) {
        List<TStileCapelliDto> tStileCapelliDtoList = new ArrayList<>() ;
        List<TStileCapelli> tStileCapelliList = tStileCapelliRepository.findAll(
                dataLessThanDIniVal(dataRiferiStileCapelli)
                        .and(dataRifGreaterThanDFinVal(dataRiferiStileCapelli)),
                Sort.by(Sort.Direction.ASC, "stileCapelli")) ;
        if(!CollectionUtils.isEmpty(tStileCapelliList)) {
            tStileCapelliList.stream().forEach(tStileCapelli -> {
                tStileCapelliDtoList.add(tStileCapelliMapper.tStileCapelliToTStileCapelliDto(tStileCapelli)) ;
            });
        }

        return tStileCapelliDtoList;
    }

    @Override
    public TStileCapelliDto findByKeyAndDescrAndData(String idStileCapelli, String descStileCapelli, LocalDate dataRiferimento) throws NoDataException {
        Optional<TStileCapelli> optionalTStileCapelli = tStileCapelliRepository.findOne(
                idStileCapelliEqual(idStileCapelli)
                        .and(descrizioneStileCapelliEqual(descStileCapelli))
                        .and(TStileCapelliSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TStileCapelliSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TStileCapelli tStileCapelli = optionalTStileCapelli.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Stile Capelli"));

        return tStileCapelliMapper.tStileCapelliToTStileCapelliDto(tStileCapelli);
    }

    @Override
    public TStileCapelliDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException {
        Optional<TStileCapelli> optionalTStileCapelli = tStileCapelliRepository.findOne(
                idStileCapelliEqual(id)
                        .and(TStileCapelliSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TStileCapelliSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TStileCapelli tStileCapelli = optionalTStileCapelli.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Stile Capelli"));

        return tStileCapelliMapper.tStileCapelliToTStileCapelliDto(tStileCapelli);
    }

    @Override
    public TStileCapelliDto getStileCapelliByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException {

        return StringUtils.isBlank(descrizione)
                ? this.findByIdAndData(id, dataRif)
                : this.findByKeyAndDescrAndData(id, descrizione, dataRif);
    }
}