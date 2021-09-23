package com.first.meridian.response;

import com.first.meridian.domain.IpAddress;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class IpPoolAndAddressResponse {

    private Long id;
    private String description;
    private Long totalCapacity;
    private Long usedCapacity;
    private Long lowerBound;
    private Long upperBound;
    private List<IpAddress> ipAddresses;

}
