package it.interno.caratteristichepersonafisica.mapper;

import it.interno.caratteristichepersonafisica.dto.TDentiDto;
import it.interno.caratteristichepersonafisica.entity.TDenti;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TDentiMapper {

    TDenti tDentiDtoToTDenti(TDentiDto tDentiDto) ;

    @InheritInverseConfiguration()
    TDentiDto tDentiToTDentiDto(TDenti tDenti) ;
}
