package com.first.meridian.controller;

import com.first.meridian.command.IpRequestCommand;
import com.first.meridian.service.IpService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/v1")
public class IpConfigController {

    private static final Logger logger = LoggerFactory.getLogger(IpConfigController.class);

    @Autowired
    private IpService ipService;

    @PostMapping(consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    @Operation(method = "POST", summary = "Creates Ip Addresses for requested pool id",
            description = "System will create requested number of pool id, minimum of 1 and maximum of 250")
    public void createIpAddresses(@RequestBody IpRequestCommand command) {
        logger.info("Request received to create ip address for ip id {}", command.getPoolId());

    }
}
