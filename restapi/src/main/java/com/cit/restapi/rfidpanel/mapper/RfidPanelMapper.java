package com.cit.restapi.rfidpanel.mapper;

import com.cit.common.om.access.device.RfidReaderPanel;
import com.cit.restapi.common.mapper.CommonMapper;
import com.cit.restapi.rfidpanel.dto.LocationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

/**
 * Created by odziea on 11/18/2018.
 */
@Component
@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RfidPanelMapper extends CommonMapper{


    LocationDto rfidReaderPanelToLocationDto(final RfidReaderPanel panel);
}
