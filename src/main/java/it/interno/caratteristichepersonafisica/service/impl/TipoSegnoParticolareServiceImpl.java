package it.interno.caratteristichepersonafisica.service.impl;

import it.interno.caratteristichepersonafisica.dto.TSegnoParticolareDto;
import it.interno.caratteristichepersonafisica.entity.TSegnoParticolare;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.mapper.TSegnoParticolareMapper;
import it.interno.caratteristichepersonafisica.repository.TSegnoParticolareRepository;
import it.interno.caratteristichepersonafisica.repository.specifications.TSegnoParticolareSpecifications;
import it.interno.caratteristichepersonafisica.service.TipoSegnoParticolareService;
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

import static it.interno.caratteristichepersonafisica.repository.specifications.TSegnoParticolareSpecifications.*;

@Service
@Transactional(readOnly = true)
public class TipoSegnoParticolareServiceImpl implements TipoSegnoParticolareService {

    @Autowired
    TSegnoParticolareRepository tSegnoParticolareRepository ;

    @Autowired
    TSegnoParticolareMapper tSegnoParticolareMapper ;

    @Override
    public List<TSegnoParticolareDto> getTipoSegnoParticolareByDataRif(LocalDate dataRiferiSegnoParticolare) {
        List<TSegnoParticolareDto> tSegnoParticolareDtoList = new ArrayList<>() ;
        List<TSegnoParticolare> tSegnoParticolareList = tSegnoParticolareRepository.findAll(
                dataLessThanDIniVal(dataRiferiSegnoParticolare)
                        .and(dataRifGreaterThanDFinVal(dataRiferiSegnoParticolare)),
                Sort.by(Sort.Direction.ASC, "segnoParticolare")) ;
        if(!CollectionUtils.isEmpty(tSegnoParticolareList)) {
            tSegnoParticolareList.stream().forEach(tSegnoParticolare -> {
                tSegnoParticolareDtoList.add(tSegnoParticolareMapper.tSegnoParticolareToTSegnoParticolareDto(tSegnoParticolare)) ;
            });
        }

        return tSegnoParticolareDtoList;
    }

    private List<TSegnoParticolareDto> findByKeyAndDescrAndData(List<String> idSegnoParticolare, String descSegnoParticolare, LocalDate dataRiferimento) {

        List<TSegnoParticolareDto> tSegnoParticolareDtoList = new ArrayList<>();
        tSegnoParticolareRepository.findAll(
                segnoParticolareIn(idSegnoParticolare)
                        .and(descrizioneSegnoParticolareEqual(descSegnoParticolare))
                        .and(TSegnoParticolareSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TSegnoParticolareSpecifications.dataRifGreaterThanDFinVal(dataRiferimento))
        ).forEach(segnoParticolare -> tSegnoParticolareDtoList.add(tSegnoParticolareMapper.tSegnoParticolareToTSegnoParticolareDto(segnoParticolare)));


        return tSegnoParticolareDtoList;
    }

    private List<TSegnoParticolareDto> findByIdAndData(List<String> idSegnoParticolare, LocalDate dataRiferimento) {
        List<TSegnoParticolareDto> tSegnoParticolareDtoList = new ArrayList<>();
        tSegnoParticolareRepository.findAll(
                segnoParticolareIn(idSegnoParticolare)
                        .and(TSegnoParticolareSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TSegnoParticolareSpecifications.dataRifGreaterThanDFinVal(dataRiferimento))
        ).forEach(segnoParticolare -> tSegnoParticolareDtoList.add(tSegnoParticolareMapper.tSegnoParticolareToTSegnoParticolareDto(segnoParticolare)));


        return tSegnoParticolareDtoList;
    }

    @Override
    public List<TSegnoParticolareDto> getSegnoParticolareByIdAndDescrizioneAndData(List<String> idSegnoParticolare, String descrizione, LocalDate dataRif) {

        return StringUtils.isBlank(descrizione)
                ? this.findByIdAndData(idSegnoParticolare, dataRif)
                : this.findByKeyAndDescrAndData(idSegnoParticolare, descrizione, dataRif);
    }
}