package it.interno.caratteristichepersonafisica.service.impl;

import it.interno.caratteristichepersonafisica.dto.TFormaDelVisoDto;
import it.interno.caratteristichepersonafisica.entity.TFormaDelViso;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.mapper.TFormaDelVisoMapper;
import it.interno.caratteristichepersonafisica.repository.TFormaDelVisoRepository;
import it.interno.caratteristichepersonafisica.repository.specifications.TFormaDelVisoSpecifications;
import it.interno.caratteristichepersonafisica.service.TipoFormaDelVisoService;
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

import static it.interno.caratteristichepersonafisica.repository.specifications.TFormaDelVisoSpecifications.*;

@Service
@Transactional(readOnly = true)
public class TipoFormaDelVisoServiceImpl implements TipoFormaDelVisoService {

    @Autowired
    TFormaDelVisoRepository tFormaDelVisoRepository ;

    @Autowired
    TFormaDelVisoMapper tFormaDelVisoMapper ;

    @Override
    public List<TFormaDelVisoDto> getTipoFormaDelVisoByDataRif(LocalDate dataRiferimento) {
        List<TFormaDelVisoDto> tFormaDelVisoDtoList = new ArrayList<>() ;
        List<TFormaDelViso> tFormaDelVisoList = tFormaDelVisoRepository.findAll(
                dataLessThanDIniVal(dataRiferimento)
                        .and(dataRifGreaterThanDFinVal(dataRiferimento)),
                Sort.by(Sort.Direction.ASC, "formaDelViso")) ;
        if(!CollectionUtils.isEmpty(tFormaDelVisoList)) {
            tFormaDelVisoList.stream().forEach(tFormaDelViso -> {
                tFormaDelVisoDtoList.add(tFormaDelVisoMapper.tFormaDelVisoToTFormaDelVisoDto(tFormaDelViso)) ;
            });
        }

        return tFormaDelVisoDtoList;
    }

    @Override
    public TFormaDelVisoDto findByKeyAndDescrAndData(String idFormaDelViso, String descFormaDelViso, LocalDate dataRiferimento) throws NoDataException {
        Optional<TFormaDelViso> optionalTFormaDelViso = tFormaDelVisoRepository.findOne(
                idFormaDelVisoEqual(idFormaDelViso)
                        .and(descrizioneFormaDelVisoEqual(descFormaDelViso))
                        .and(TFormaDelVisoSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TFormaDelVisoSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TFormaDelViso tFormaDelViso = optionalTFormaDelViso.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Forma Viso"));

        return tFormaDelVisoMapper.tFormaDelVisoToTFormaDelVisoDto(tFormaDelViso);
    }

    @Override
    public TFormaDelVisoDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException {
        Optional<TFormaDelViso> optionalTFormaDelViso = tFormaDelVisoRepository.findOne(
                idFormaDelVisoEqual(id)
                        .and(TFormaDelVisoSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TFormaDelVisoSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TFormaDelViso tFormaDelViso = optionalTFormaDelViso.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Forma Viso"));

        return tFormaDelVisoMapper.tFormaDelVisoToTFormaDelVisoDto(tFormaDelViso);
    }

    @Override
    public TFormaDelVisoDto getFormaDelVisoByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException {

        return StringUtils.isBlank(descrizione)
                ? this.findByIdAndData(id, dataRif)
                : this.findByKeyAndDescrAndData(id, descrizione, dataRif);
    }
}