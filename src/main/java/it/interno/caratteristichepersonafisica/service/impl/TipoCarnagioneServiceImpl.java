package it.interno.caratteristichepersonafisica.service.impl;

import it.interno.caratteristichepersonafisica.dto.TCarnagioneDto;
import it.interno.caratteristichepersonafisica.entity.TCarnagione;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.mapper.TCarnagioneMapper;
import it.interno.caratteristichepersonafisica.repository.TCarnagioneRepository;
import it.interno.caratteristichepersonafisica.repository.specifications.TCarnagioneSpecifications;
import it.interno.caratteristichepersonafisica.service.TipoCarnagioneService;
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

import static it.interno.caratteristichepersonafisica.repository.specifications.TCarnagioneSpecifications.*;

@Service
@Transactional(readOnly = true)
public class TipoCarnagioneServiceImpl implements TipoCarnagioneService {

    @Autowired
    TCarnagioneRepository tCarnagioneRepository ;

    @Autowired
    TCarnagioneMapper tCarnagioneMapper ;

    @Override
    public List<TCarnagioneDto> getTipoCarnagioneByDataRif(LocalDate dataRiferimento) {
        List<TCarnagioneDto> tCarnagioneDtoList = new ArrayList<>() ;
        List<TCarnagione> tCarnagioneList = tCarnagioneRepository.findAll(
                dataLessThanDIniVal(dataRiferimento)
                        .and(dataRifGreaterThanDFinVal(dataRiferimento)),
                Sort.by(Sort.Direction.ASC, "carnagione")) ;
        if(!CollectionUtils.isEmpty(tCarnagioneList)) {
            tCarnagioneList.stream().forEach(tCarnagione -> {
                tCarnagioneDtoList.add(tCarnagioneMapper.tCarnagioneToTCarnagioneDto(tCarnagione)) ;
            });
        }

        return tCarnagioneDtoList;
    }

    private List<TCarnagioneDto> findByKeyAndDescrAndData(List<String> idCarnagione, String descCarnagione, LocalDate dataRiferimento) {
        List<TCarnagioneDto> carnagioneList = new ArrayList<>();
        tCarnagioneRepository.findAll(
                idCarnagioneIn(idCarnagione)
                        .and(descrizioneCarnagioneEqual(descCarnagione))
                        .and(dataLessThanDIniVal(dataRiferimento))
                        .and(dataRifGreaterThanDFinVal(dataRiferimento))
        ).forEach(tCarnagione -> {
            carnagioneList.add(tCarnagioneMapper.tCarnagioneToTCarnagioneDto(tCarnagione));
        });

        return carnagioneList;
    }

    private List<TCarnagioneDto> findByIdAndData(List<String> idCarnagione, LocalDate dataRiferimento) {
       List<TCarnagioneDto> carnagioneList = new ArrayList<>();
        tCarnagioneRepository.findAll(
                idCarnagioneIn(idCarnagione)
                        .and(dataLessThanDIniVal(dataRiferimento))
                        .and(dataRifGreaterThanDFinVal(dataRiferimento))
        ).forEach(tCarnagione -> carnagioneList.add(tCarnagioneMapper.tCarnagioneToTCarnagioneDto(tCarnagione)));

        return carnagioneList;
    }

    @Override
    public List<TCarnagioneDto> getCarnagioneByIdAndDescrizioneAndData(List<String> idCarnagione, String descrizione, LocalDate dataRif) {

        return StringUtils.isBlank(descrizione)
                ? this.findByIdAndData(idCarnagione, dataRif)
                : this.findByKeyAndDescrAndData(idCarnagione, descrizione, dataRif);
    }

}