package com.first.meridian.service;

import com.first.meridian.domain.IpAddress;
import com.first.meridian.repository.IpAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IpService {

    @Autowired
    private IpAddressRepository repository;

    public boolean isInvalidPoolIdAndRequestedPools(Long poolId, Long numberOfPoolsToCreate) {

        if (null != poolId) {
            Optional<IpAddress> ipAddress = repository.findById(poolId);
            return true;
        }
        return false;
    }
}
