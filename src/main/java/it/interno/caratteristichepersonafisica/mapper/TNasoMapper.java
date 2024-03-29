package it.interno.caratteristichepersonafisica.mapper;

import it.interno.caratteristichepersonafisica.dto.TNasoDto;
import it.interno.caratteristichepersonafisica.entity.TNaso;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TNasoMapper {

    TNaso tNasoDtoToTNaso(TNasoDto tNasoDto) ;

    @InheritInverseConfiguration()
    TNasoDto tNasoToTNasoDto(TNaso tNaso) ;
}
