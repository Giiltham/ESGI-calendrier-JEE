package fr.esgi.calendrier_CB_EE.mapper;

import fr.esgi.calendrier_CB_EE.business.Gif;
import fr.esgi.calendrier_CB_EE.business.JourCalendrier;
import fr.esgi.calendrier_CB_EE.dto.JourCalendrierDto;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface JourCalendrierMapper {
    JourCalendrier toEntity(JourCalendrierDto jardinierDto);

    JourCalendrierDto toDto(JourCalendrier jardinier);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    JourCalendrier partialUpdate(JourCalendrierDto jardinierDto,
                            @MappingTarget JourCalendrier jardinier);
}