package com.first.meridian.controller;

import com.first.meridian.command.IpRequestCommand;
import com.first.meridian.service.IpService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IpConfigControllerTest {

    @Mock
    private IpService service;

    @InjectMocks
    private IpConfigController configController;

    @Before
    public void setUp() {
        configController = new IpConfigController();
    }

    @Test
    public void should_test() {
        configController.createIpAddresses(command());
    }


    private IpRequestCommand command() {
        return IpRequestCommand.builder()
                .poolId(1L)
                .description("test")
                .numberOfPoolsToCreate(10L)
                .build();
    }
}
