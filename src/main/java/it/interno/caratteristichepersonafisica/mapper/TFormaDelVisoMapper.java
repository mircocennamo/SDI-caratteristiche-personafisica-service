package it.interno.caratteristichepersonafisica.mapper;

import it.interno.caratteristichepersonafisica.dto.TFormaDelVisoDto;
import it.interno.caratteristichepersonafisica.entity.TFormaDelViso;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TFormaDelVisoMapper {

    TFormaDelViso tFormaDelVisoDtoToTFormaDelViso(TFormaDelVisoDto tFormaDelVisoDto) ;

    @InheritInverseConfiguration()
    TFormaDelVisoDto tFormaDelVisoToTFormaDelVisoDto(TFormaDelViso tFormaDelViso) ;
}
