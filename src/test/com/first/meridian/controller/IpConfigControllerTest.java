package com.first.meridian.controller;

import com.first.meridian.service.IpWriteService;
import com.first.meridian.util.IpConfigTestUtils;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class IpConfigControllerTest {

    @Mock
    private IpWriteService service;

    @InjectMocks
    private IpConfigController configController;

    @Test
    public void should_return_invalidRequest_when_requested_pool_size_is_more_than_used_capacity() {
        // Given
        val command = IpConfigTestUtils.command();
        when(service.isInvalidPoolIdAndRequestedPools(command.getPoolId(), command.getNumberOfAddressToCreate()))
                .thenReturn(true);
        // When
        val response = configController.createIpAddresses(command);
        // Then
        assertThat(response.getStatusCode()).isEqualTo(BAD_REQUEST);
        assertThat(response.getBody().getPoolId()).isEqualTo(command.getPoolId());
        assertThat(response.getBody().getResponseMessage()).isEqualTo("Invalid pool id or size requested is exceeded");
    }

    @Test
    public void should_create_pool_addresses_for_given_pool_id() {
        // Given
        val command = IpConfigTestUtils.command();
        val ipAddressResponse = IpConfigTestUtils.ipAddressResponse();
        when(service.isInvalidPoolIdAndRequestedPools(command.getPoolId(), command.getNumberOfAddressToCreate()))
                .thenReturn(false);
        when(service.createIpAddress(command)).thenReturn(ipAddressResponse);
        // When
        val response = configController.createIpAddresses(command);
        // Then
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody().getPoolId()).isEqualTo(command.getPoolId());
        assertThat(response.getBody().getResponseMessage()).isEqualTo(ipAddressResponse.getResponseMessage());
        assertThat(response.getBody().getIpAddresses().size()).isEqualTo(ipAddressResponse.getIpAddresses().size());
    }
}
