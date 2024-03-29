package it.interno.caratteristichepersonafisica.mapper;

import it.interno.caratteristichepersonafisica.dto.TStileCapelliDto;
import it.interno.caratteristichepersonafisica.entity.TStileCapelli;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TStileCapelliMapper {

    TStileCapelli tStileCapelliDtoToTStileCapelli(TStileCapelliDto tStileCapelliDto) ;

    @InheritInverseConfiguration()
    TStileCapelliDto tStileCapelliToTStileCapelliDto(TStileCapelli tStileCapelli) ;
}
