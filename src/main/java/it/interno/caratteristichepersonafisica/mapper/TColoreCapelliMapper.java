package it.interno.caratteristichepersonafisica.mapper;

import it.interno.caratteristichepersonafisica.dto.TColoreCapelliDto;
import it.interno.caratteristichepersonafisica.entity.TColoreCapelli;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TColoreCapelliMapper {

    TColoreCapelli tColoreCapelliDtoToTColoreCapelli(TColoreCapelliDto tColoreCapelliDto) ;

    @InheritInverseConfiguration()
    TColoreCapelliDto tColoreCapelliToTColoreCapelliDto(TColoreCapelli tColoreCapelli) ;
}
