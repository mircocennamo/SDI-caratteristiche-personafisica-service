package it.interno.caratteristichepersonafisica.mapper;

import it.interno.caratteristichepersonafisica.dto.TMentoDto;
import it.interno.caratteristichepersonafisica.entity.TMento;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TMentoMapper {

    TMento tMentoDtoToTMento(TMentoDto tMentoDto) ;

    @InheritInverseConfiguration()
    TMentoDto tMentoToTMentoDto(TMento tMento) ;
}
