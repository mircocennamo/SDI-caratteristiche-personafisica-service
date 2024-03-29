package it.interno.caratteristichepersonafisica.service.impl;

import it.interno.caratteristichepersonafisica.dto.TPericolositaDto;
import it.interno.caratteristichepersonafisica.entity.TPericolosita;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.mapper.TPericolositaMapper;
import it.interno.caratteristichepersonafisica.repository.TPericolositaRepository;
import it.interno.caratteristichepersonafisica.repository.specifications.TPericolositaSpecifications;
import it.interno.caratteristichepersonafisica.service.TipoPericolositaService;
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

import static it.interno.caratteristichepersonafisica.repository.specifications.TPericolositaSpecifications.*;

@Service
@Transactional(readOnly = true)
public class TipoPericolositaServiceImpl implements TipoPericolositaService {

    @Autowired
    TPericolositaRepository tPericolositaRepository ;

    @Autowired
    TPericolositaMapper tPericolositaMapper ;

    @Override
    public List<TPericolositaDto> getTipoPericolositaByDataRif(LocalDate dataRiferiPericolosita) {
        List<TPericolositaDto> tPericolositaDtoList = new ArrayList<>() ;
        List<TPericolosita> tPericolositaList = tPericolositaRepository.findAll(
                dataLessThanDIniVal(dataRiferiPericolosita)
                        .and(dataRifGreaterThanDFinVal(dataRiferiPericolosita)),
                Sort.by(Sort.Direction.ASC, "pericolosita")) ;
        if(!CollectionUtils.isEmpty(tPericolositaList)) {
            tPericolositaList.stream().forEach(tPericolosita -> {
                tPericolositaDtoList.add(tPericolositaMapper.tPericolositaToTPericolositaDto(tPericolosita)) ;
            });
        }

        return tPericolositaDtoList;
    }

    private List<TPericolositaDto> findByKeyAndDescrAndData(List<String> idPericolosita, String descPericolosita, LocalDate dataRiferimento) {
        
        List<TPericolositaDto> tPericolositaDtoList = new ArrayList<>();
        tPericolositaRepository.findAll(
                        idPericolositaIn(idPericolosita)
                                .and(descrizionePericolositaEqual(descPericolosita))
                                .and(dataLessThanDIniVal(dataRiferimento))
                                .and(dataRifGreaterThanDFinVal(dataRiferimento)))
                .forEach(tPericolosita -> tPericolositaDtoList.add(tPericolositaMapper.tPericolositaToTPericolositaDto(tPericolosita)));

        return tPericolositaDtoList;
    }

    private List<TPericolositaDto> findByIdAndData(List<String> idPericolosita, LocalDate dataRiferimento)  {
        List<TPericolositaDto> tPericolositaDtoList = new ArrayList<>();
        tPericolositaRepository.findAll(
                idPericolositaIn(idPericolosita)
                        .and(dataLessThanDIniVal(dataRiferimento))
                        .and(dataRifGreaterThanDFinVal(dataRiferimento)))
                .forEach(tPericolosita -> tPericolositaDtoList.add(tPericolositaMapper.tPericolositaToTPericolositaDto(tPericolosita)));

        return tPericolositaDtoList;
    }

    @Override
    public List<TPericolositaDto> getPericolositaByIdAndDescrizioneAndData(List<String> idPericolosita, String descrizione, LocalDate dataRif) {

        return StringUtils.isBlank(descrizione)
                ? this.findByIdAndData(idPericolosita, dataRif)
                : this.findByKeyAndDescrAndData(idPericolosita, descrizione, dataRif);
    }

}