package it.interno.caratteristichepersonafisica.mapper;

import it.interno.caratteristichepersonafisica.dto.TSegnoParticolareDto;
import it.interno.caratteristichepersonafisica.entity.TSegnoParticolare;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TSegnoParticolareMapper {

    TSegnoParticolare tSegnoParticolareDtoToTSegnoParticolare(TSegnoParticolareDto tSegnoParticolareDto) ;

    @InheritInverseConfiguration()
    TSegnoParticolareDto tSegnoParticolareToTSegnoParticolareDto(TSegnoParticolare tSegnoParticolare) ;
}
