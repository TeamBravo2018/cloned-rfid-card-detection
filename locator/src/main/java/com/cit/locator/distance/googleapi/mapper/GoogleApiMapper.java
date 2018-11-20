package com.cit.locator.distance.googleapi.mapper;

import com.cit.locator.distance.googleapi.dto.GoogleResponseDTO;
import com.cit.locator.distance.googleapi.dto.GoogleResponseRowDTO;
import com.cit.locator.distance.om.Distance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Created by odziea on 11/20/2018.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface GoogleApiMapper {

    @Mappings({
            @Mapping(target="length", source="rows", qualifiedByName = "googleResponseToDistance"),
            @Mapping(target="duration", source="rows", qualifiedByName = "googleResponseToDuration"),
            @Mapping(target="travelMeans", ignore=true)
    })
    Distance googleResponseDtoToDistance(GoogleResponseDTO googleResponseDTO);

    default double googleResponseToDistance(List<GoogleResponseRowDTO> responseRowDTOS) {
        return responseRowDTOS.get(0).getElements().get(0).getDistance().getValue();
    }

    default int googleResponseToDuration(List<GoogleResponseRowDTO> responseRowDTOS) {
        return responseRowDTOS.get(0).getElements().get(0).getDuration().getValue();
    }


}
