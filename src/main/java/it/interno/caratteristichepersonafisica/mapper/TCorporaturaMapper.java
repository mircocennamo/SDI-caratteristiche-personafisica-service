package it.interno.caratteristichepersonafisica.mapper;

import it.interno.caratteristichepersonafisica.dto.TCorporaturaDto;
import it.interno.caratteristichepersonafisica.entity.TCorporatura;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TCorporaturaMapper {

    TCorporatura tCorporaturaDtoToTCorporatura(TCorporaturaDto tCorporaturaDto) ;

    @InheritInverseConfiguration()
    TCorporaturaDto tCorporaturaToTCorporaturaDto(TCorporatura tCorporatura) ;
}
