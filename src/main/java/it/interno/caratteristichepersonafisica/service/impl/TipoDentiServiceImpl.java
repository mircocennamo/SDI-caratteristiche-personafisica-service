package it.interno.caratteristichepersonafisica.service.impl;

import it.interno.caratteristichepersonafisica.dto.TDentiDto;
import it.interno.caratteristichepersonafisica.entity.TDenti;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.mapper.TDentiMapper;
import it.interno.caratteristichepersonafisica.repository.TDentiRepository;
import it.interno.caratteristichepersonafisica.repository.specifications.TDentiSpecifications;
import it.interno.caratteristichepersonafisica.service.TipoDentiService;
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

import static it.interno.caratteristichepersonafisica.repository.specifications.TDentiSpecifications.*;

@Service
@Transactional(readOnly = true)
public class TipoDentiServiceImpl implements TipoDentiService {

    @Autowired
    TDentiRepository tDentiRepository ;

    @Autowired
    TDentiMapper tDentiMapper ;

    @Override
    public List<TDentiDto> getTipoDentiByDataRif(LocalDate dataRiferimento) {
        List<TDentiDto> tDentiDtoList = new ArrayList<>() ;
        List<TDenti> tDentiList = tDentiRepository.findAll(
                dataLessThanDIniVal(dataRiferimento)
                        .and(dataRifGreaterThanDFinVal(dataRiferimento)),
                Sort.by(Sort.Direction.ASC, "denti")) ;
        if(!CollectionUtils.isEmpty(tDentiList)) {
            tDentiList.stream().forEach(tDenti -> {
                tDentiDtoList.add(tDentiMapper.tDentiToTDentiDto(tDenti)) ;
            });
        }

        return tDentiDtoList;
    }

    @Override
    public TDentiDto findByKeyAndDescrAndData(String idDenti, String descDenti, LocalDate dataRiferimento) throws NoDataException {
        Optional<TDenti> optionalTDenti = tDentiRepository.findOne(
                idDentiEqual(idDenti)
                        .and(descrizioneDentiEqual(descDenti))
                        .and(TDentiSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TDentiSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TDenti tDenti = optionalTDenti.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Denti"));

        return tDentiMapper.tDentiToTDentiDto(tDenti);
    }

    @Override
    public TDentiDto findByIdAndData(String id, LocalDate dataRiferimento) throws NoDataException {
        Optional<TDenti> optionalTDenti = tDentiRepository.findOne(
                idDentiEqual(id)
                        .and(TDentiSpecifications.dataLessThanDIniVal(dataRiferimento))
                        .and(TDentiSpecifications.dataRifGreaterThanDFinVal(dataRiferimento)));
        TDenti tDenti = optionalTDenti.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Denti"));

        return tDentiMapper.tDentiToTDentiDto(tDenti);
    }

    @Override
    public TDentiDto getDentiByIdAndDescrizioneAndData(String id, String descrizione, LocalDate dataRif) throws NoDataException {

        return StringUtils.isBlank(descrizione)
                ? this.findByIdAndData(id, dataRif)
                : this.findByKeyAndDescrAndData(id, descrizione, dataRif);
    }
}