package it.interno.caratteristichepersonafisica.mapper;

import it.interno.caratteristichepersonafisica.dto.TDettagliOcchiDto;
import it.interno.caratteristichepersonafisica.entity.TDettagliOcchi;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TDettagliOcchiMapper {

    TDettagliOcchi tDettagliOcchiDtoToTDettagliOcchi(TDettagliOcchiDto tDettagliOcchiDto) ;

    @InheritInverseConfiguration()
    TDettagliOcchiDto tDettagliOcchiToTDettagliOcchiDto(TDettagliOcchi tDettagliOcchi) ;
}
