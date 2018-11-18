package com.cit.locator.panellocator.uuidpanellocator.mapper;

import com.cit.common.om.access.device.RfidReaderPanel;
import com.cit.common.om.location.Address;
import com.cit.common.om.location.Building;
import com.cit.locator.panellocator.uuidpanellocator.dto.UuidLocatorLocationDto;
import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

/**
 * Created by odziea on 11/18/2018.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UuidLocatorLocationMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source="altitude", target = "geoLocation.z"),
            @Mapping(source="coordinates.longitude", target = "geoLocation.x"),
            @Mapping(source="coordinates.latitude", target = "geoLocation.y"),
            @Mapping(source = "relativeLocation", target = "building")
    })
    RfidReaderPanel dtoToDomain(UuidLocatorLocationDto uuidLocatorLocationDto);

    default String buildingToRelativeLocation(Building building) {
        return building.getAddress().getAddressLine1() + ", " + building.getAddress().getAddressLine2();
    }

    default Building relativeLocationToBuilding(String relativeLocation) {

        Building building = new Building();

        if (!StringUtils.isEmpty(relativeLocation)) {
            String[] addressTokens = StringUtils.split(relativeLocation, ",");

            if (addressTokens.length > 0) {
                building.setName(addressTokens[0]);
                Address address = new Address();

                String addressLine1 = "";
                String addressLine2 = "";
                for (int i = 0; i < addressTokens.length; i++) {

                    if (i < (addressTokens.length / 2)) {
                        addressLine1 += addressTokens[i];

                        if (i != (addressTokens.length / 2) - 1) {
                            addressLine1 += ", ";
                        }
                    } else {
                        addressLine2 += addressTokens[i];
                        if (i != 0 && i >= addressTokens.length/2) {

                            if (i != (addressTokens.length / 2 + 1) && i != addressTokens.length - 1) {
                                addressLine2 += ", ";
                            }

                        }
                    }

                }

                address.setAddressLine1(addressLine1);
                address.setAddressLine2(addressLine2);
                address.setCountry(addressTokens[addressTokens.length - 1]);

                building.setAddress(address);
            }


        }

        return building;
    }

}