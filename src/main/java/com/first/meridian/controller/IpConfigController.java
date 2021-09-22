package com.first.meridian.controller;

import com.first.meridian.domain.IpAddress;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/v1")
public class IpConfigController {

    private static final Logger logger = LoggerFactory.getLogger(IpConfigController.class);

    @PostMapping(consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    @Operation(method = "POST", summary = "",
            description = "")
    public void createIpAddresses(@Valid @RequestBody IpAddress ipAddress) {
    }
}
