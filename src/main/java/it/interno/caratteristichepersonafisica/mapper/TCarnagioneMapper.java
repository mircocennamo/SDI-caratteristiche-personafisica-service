package it.interno.caratteristichepersonafisica.mapper;

import it.interno.caratteristichepersonafisica.dto.TCarnagioneDto;
import it.interno.caratteristichepersonafisica.entity.TCarnagione;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TCarnagioneMapper {

    TCarnagione tCarnagioneDtoToTCarnagione(TCarnagioneDto tCarnagioneDto) ;

    @InheritInverseConfiguration()
    TCarnagioneDto tCarnagioneToTCarnagioneDto(TCarnagione tCarnagione) ;
}
