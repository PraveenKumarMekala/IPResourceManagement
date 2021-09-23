package com.first.meridian.util;

import com.first.meridian.command.IpRequestCommand;
import com.first.meridian.domain.IpAddress;
import com.first.meridian.domain.IpAddressId;
import com.first.meridian.domain.IpPool;
import com.first.meridian.response.IpAddressResponse;
import com.first.meridian.response.IpPoolAndAddressResponse;

import java.util.Arrays;

import static java.util.Arrays.asList;

public class IpConfigTestUtils {

    public static IpRequestCommand command() {
        return IpRequestCommand.builder()
                .poolId(1L)
                .description("test")
                .numberOfAddressToCreate(10L)
                .build();
    }

    public static IpAddressResponse ipAddressResponse() {
        return IpAddressResponse.builder()
                .poolId(1L)
                .responseMessage("Addresses created successfully")
                .ipAddresses(asList(ipAddress()))
                .build();
    }

    public static IpAddress ipAddress() {
        return IpAddress.builder()
                .id(IpAddressId.builder().ipAddressId(10L).ipPoolId(10L).build())
                .build();
    }

    public static IpPool ipPool() {
        return IpPool.builder()
                .id(1L)
                .description("test")
                .ipAddresses(asList(ipAddress()))
                .lowerBound(0L)
                .upperBound(250L)
                .totalCapacity(250L)
                .usedCapacity(100L)
                .build();
    }

    public static IpPool resultIpPool() {
        return IpPool.builder()
                .id(1L)
                .ipAddresses(asList(ipAddress()))
                .usedCapacity(150L)
                .build();
    }


    public static IpPoolAndAddressResponse ipPoolAndAddressResponse() {
        return IpPoolAndAddressResponse.builder()
                .id(1L)
                .description("test")
                .lowerBound(0L)
                .upperBound(250L)
                .usedCapacity(250L)
                .ipAddresses(asList(ipAddress()))
                .build();
    }
}
