package it.interno.caratteristichepersonafisica.service.impl;

import it.interno.caratteristichepersonafisica.dto.TColoreOcchiDto;
import it.interno.caratteristichepersonafisica.entity.TColoreOcchi;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.mapper.TColoreOcchiMapper;
import it.interno.caratteristichepersonafisica.repository.TColoreOcchiRepository;
import it.interno.caratteristichepersonafisica.repository.specifications.TColoreOcchiSpecifications;
import it.interno.caratteristichepersonafisica.service.TipoColoreOcchiService;
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

import static it.interno.caratteristichepersonafisica.repository.specifications.TColoreOcchiSpecifications.*;

@Service
@Transactional(readOnly = true)
public class TipoColoreOcchiServiceImpl implements TipoColoreOcchiService {

    @Autowired
    TColoreOcchiRepository tColoreOcchiRepository ;

    @Autowired
    TColoreOcchiMapper tColoreOcchiMapper ;

    @Override
    public List<TColoreOcchiDto> getTipoColoreOcchiByDataRif(LocalDate dataRiferimento) {
        List<TColoreOcchiDto> tColoreOcchiDtoList = new ArrayList<>() ;
        List<TColoreOcchi> tColoreOcchiList = tColoreOcchiRepository.findAll(
                dataLessThanDIniVal(dataRiferimento)
                        .and(dataRifGreaterThanDFinVal(dataRiferimento)),
                Sort.by(Sort.Direction.ASC, "coloreOcchi")) ;
        if(!CollectionUtils.isEmpty(tColoreOcchiList)) {
            tColoreOcchiList.stream().forEach(tColoreOcchi -> {
                tColoreOcchiDtoList.add(tColoreOcchiMapper.tColoreOcchiToTColoreOcchiDto(tColoreOcchi)) ;
            });
        }

        return tColoreOcchiDtoList;
    }

    @Override
    public TColoreOcchiDto findByKeyAndDescrAndData(String idColoreOcchi, String descColoreOcchi, LocalDate dataRiferimento) throws NoDataException {
        Optional<TColoreOcchi> optionalTColoreOcchi = tColoreOcchiRepository.findOne(
                idColoreOcchiEqual(idColoreOcchi)
                        .and(descrizioneColoreOcchiEqual(descColoreOcchi))
                        .and(TColoreOcchiSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TColoreOcchiSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TColoreOcchi tColoreOcchi = optionalTColoreOcchi.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Colore Occhi"));

        return tColoreOcchiMapper.tColoreOcchiToTColoreOcchiDto(tColoreOcchi);
    }

    @Override
    public TColoreOcchiDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException {

        Optional<TColoreOcchi> optionalTColoreOcchi = tColoreOcchiRepository.findOne(
                idColoreOcchiEqual(id)
                        .and(TColoreOcchiSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TColoreOcchiSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TColoreOcchi tColoreOcchi = optionalTColoreOcchi.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Colore Occhi"));

        return tColoreOcchiMapper.tColoreOcchiToTColoreOcchiDto(tColoreOcchi);
    }

    @Override
    public TColoreOcchiDto getColoreOcchiByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException {

        return StringUtils.isBlank(descrizione)
                ? this.findByIdAndData(id, dataRif)
                : this.findByKeyAndDescrAndData(id, descrizione, dataRif);
    }
}