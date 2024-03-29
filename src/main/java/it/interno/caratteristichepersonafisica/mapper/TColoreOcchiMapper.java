package it.interno.caratteristichepersonafisica.mapper;

import it.interno.caratteristichepersonafisica.dto.TColoreOcchiDto;
import it.interno.caratteristichepersonafisica.entity.TColoreOcchi;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TColoreOcchiMapper {

    TColoreOcchi tColoreOcchiDtoToTColoreOcchi(TColoreOcchiDto tColoreOcchiDto) ;

    @InheritInverseConfiguration()
    TColoreOcchiDto tColoreOcchiToTColoreOcchiDto(TColoreOcchi tColoreOcchi) ;
}
