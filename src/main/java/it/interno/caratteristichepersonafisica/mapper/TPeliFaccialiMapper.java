package it.interno.caratteristichepersonafisica.mapper;

import it.interno.caratteristichepersonafisica.dto.TPeliFaccialiDto;
import it.interno.caratteristichepersonafisica.entity.TPeliFacciali;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TPeliFaccialiMapper {

    TPeliFacciali tPeliFaccialiDtoToTPeliFacciali(TPeliFaccialiDto tPeliFaccialiDto) ;

    @InheritInverseConfiguration()
    TPeliFaccialiDto tPeliFaccialiToTPeliFaccialiDto(TPeliFacciali tPeliFacciali) ;
}
