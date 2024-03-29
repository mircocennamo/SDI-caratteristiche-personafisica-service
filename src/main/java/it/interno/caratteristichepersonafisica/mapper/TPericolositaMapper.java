package it.interno.caratteristichepersonafisica.mapper;

import it.interno.caratteristichepersonafisica.dto.TPericolositaDto;
import it.interno.caratteristichepersonafisica.entity.TPericolosita;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TPericolositaMapper {

    TPericolosita tPericolositaDtoToTPericolosita(TPericolositaDto tPericolositaDto) ;

    @InheritInverseConfiguration()
    TPericolositaDto tPericolositaToTPericolositaDto(TPericolosita tPericolosita) ;
}
