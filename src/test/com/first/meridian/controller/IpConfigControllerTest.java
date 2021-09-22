package com.first.meridian.controller;

import com.first.meridian.command.IpRequestCommand;
import com.first.meridian.service.IpService;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RunWith(MockitoJUnitRunner.class)
public class IpConfigControllerTest {

    @Mock
    private IpService service;

    @InjectMocks
    private IpConfigController configController;

    @Test
    public void should_return_invalidRequest_when_requested_pool_size_is_more_than_used_capacity() {
        // Given
        val command = command();
        Mockito.when(service.isInvalidPoolIdAndRequestedPools(command.getPoolId(), command.getNumberOfPoolsToCreate()))
                .thenReturn(true);
        // When
        val response = configController.createIpAddresses(command);
        // Then
        assertThat(response.getStatusCode()).isEqualTo(BAD_REQUEST);
        assertThat(response.getBody().getPoolId()).isEqualTo(command.getPoolId());
        assertThat(response.getBody().getResponseMessage()).isEqualTo("Invalid pool id or size requested is exceeded");
    }


    private IpRequestCommand command() {
        return IpRequestCommand.builder()
                .poolId(1L)
                .description("test")
                .numberOfPoolsToCreate(10L)
                .build();
    }
}
