package it.interno.caratteristichepersonafisica.mapper;

import it.interno.caratteristichepersonafisica.dto.TOrecchieDto;
import it.interno.caratteristichepersonafisica.entity.TOrecchie;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TOrecchieMapper {

    TOrecchie tOrecchieDtoToTOrecchie(TOrecchieDto tOrecchieDto) ;

    @InheritInverseConfiguration()
    TOrecchieDto tOrecchieToTOrecchieDto(TOrecchie tOrecchie) ;
}
