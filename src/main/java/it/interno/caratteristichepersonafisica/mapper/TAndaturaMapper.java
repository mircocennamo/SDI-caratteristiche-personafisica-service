package it.interno.caratteristichepersonafisica.mapper;

import it.interno.caratteristichepersonafisica.dto.TAndaturaDto;
import it.interno.caratteristichepersonafisica.entity.TAndatura;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TAndaturaMapper {

    TAndatura toTAndatura(TAndaturaDto tAndaturaDto) ;

    @InheritInverseConfiguration()
    TAndaturaDto toTAndaturaDto(TAndatura tAndatura) ;
}
