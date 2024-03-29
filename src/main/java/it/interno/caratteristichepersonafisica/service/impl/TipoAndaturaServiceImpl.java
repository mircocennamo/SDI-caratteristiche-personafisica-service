package it.interno.caratteristichepersonafisica.service.impl;

import it.interno.caratteristichepersonafisica.dto.TAndaturaDto;
import it.interno.caratteristichepersonafisica.entity.TAndatura;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.mapper.TAndaturaMapper;
import it.interno.caratteristichepersonafisica.repository.TAndaturaRepository;
import it.interno.caratteristichepersonafisica.service.TipoAndaturaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static it.interno.caratteristichepersonafisica.repository.specifications.TAndaturaSpecifications.*;

@Service
@Transactional(readOnly = true)
public class TipoAndaturaServiceImpl implements TipoAndaturaService {

    @Autowired
    private TAndaturaRepository tAndaturaRepository ;

    @Autowired
    private TAndaturaMapper tAndaturaMapper ;

    @Override
    public List<TAndaturaDto> getTipoAndaturaByDataRif(LocalDate dataRiferimento) {
        List<TAndaturaDto> tAndaturaDtoList = new ArrayList<>() ;
        List<TAndatura> tAndaturaList = tAndaturaRepository.findAll(
                dataLessThanDIniVal(dataRiferimento)
                        .and(dataRifGreaterThanDFinVal(dataRiferimento)),
                Sort.by(Sort.Direction.ASC, "andatura")) ;
        if(!CollectionUtils.isEmpty(tAndaturaList)) {
            tAndaturaList.forEach(tAndatura -> {
                tAndaturaDtoList.add(tAndaturaMapper.toTAndaturaDto(tAndatura)) ;
            });
        }

        return tAndaturaDtoList;
    }

    private TAndaturaDto findByKeyAndDescrAndData(String idAndatura, String descAndatura, LocalDate dataRiferimento) throws NoDataException {
        Optional<TAndatura> optionalTAndatura = tAndaturaRepository.findOne(
                         idAndaturaEqual(idAndatura)
                        .and(descrizioneAndaturaEqual(descAndatura))
                        .and(dataLessThanDIniVal(dataRiferimento))
                        .and(dataRifGreaterThanDFinVal(dataRiferimento)));
        TAndatura tAndatura = optionalTAndatura.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Andatura"));

        return tAndaturaMapper.toTAndaturaDto(tAndatura);
    }

    private TAndaturaDto findByIdAndData(String idAndatura, LocalDate dataRif) throws NoDataException {
        Optional<TAndatura> optionalTAndatura = tAndaturaRepository.findOne(
                        idAndaturaEqual(idAndatura)
                        .and(dataLessThanDIniVal(dataRif))
                        .and(dataRifGreaterThanDFinVal(dataRif)));
        TAndatura tAndatura = optionalTAndatura.orElseThrow(() -> new NoDataException("errore.campo.nonPresenteDB", "Andatura"));

        return tAndaturaMapper.toTAndaturaDto(tAndatura);
    }

    @Override
    public TAndaturaDto getTipiAndaturaByIdAndDescrizioneAndData(String idAndatura, String descrizioneAndatura, LocalDate dataRif) throws NoDataException {

        return StringUtils.isBlank(descrizioneAndatura)
                ? this.findByIdAndData(idAndatura, dataRif)
                : this.findByKeyAndDescrAndData(idAndatura, descrizioneAndatura, dataRif);

    }


}
