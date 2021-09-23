package com.first.meridian.service;

import com.first.meridian.repository.IpAddressRepository;
import com.first.meridian.repository.IpPoolRepository;
import com.first.meridian.response.IpPoolAndAddressResponse;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpPoolReadService {

    @Autowired
    private IpPoolRepository poolRepository;

    @Autowired
    private IpAddressRepository addressRepository;

    public IpPoolAndAddressResponse findByPoolId(Long id) {
        val existingPool = poolRepository.findById(id);
        if (existingPool.isPresent()) {
            val pool = existingPool.get();
            val existingAddresses = addressRepository.findByIdIpPoolId(pool.getId());
            return IpPoolAndAddressResponse.builder()
                    .id(pool.getId())
                    .description(pool.getDescription())
                    .lowerBound(pool.getLowerBound())
                    .upperBound(pool.getUpperBound())
                    .totalCapacity(pool.getTotalCapacity())
                    .usedCapacity(pool.getUsedCapacity())
                    .ipAddresses(existingAddresses)
                    .build();
        } else return null;
    }
}
