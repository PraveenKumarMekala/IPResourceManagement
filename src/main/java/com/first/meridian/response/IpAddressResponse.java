package com.first.meridian.response;

import com.first.meridian.domain.IpAddress;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class IpAddressResponse {

    private String responseMessage;

    private Long poolId;

    private List<IpAddress> ipAddresses;

}
