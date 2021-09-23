package com.first.meridian.service;

import com.first.meridian.command.IpRequestCommand;
import com.first.meridian.domain.IpAddress;
import com.first.meridian.domain.IpAddressId;
import com.first.meridian.domain.IpPool;
import com.first.meridian.repository.IpPoolRepository;
import com.first.meridian.response.IpAddressResponse;
import lombok.val;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IpWriteService {

    private static final Logger logger = LoggerFactory.getLogger(IpWriteService.class);

    public static final Long TOTAL_POOL_CAPACITY = 250L;

    public static final Long LOWER_BOUND = 0L;

    public static final Long UPPER_BOUND = 250L;

    @Autowired
    private IpPoolRepository poolRepository;

    public boolean isInvalidPoolIdAndRequestedPools(Long poolId, Long numberOfAddressToCreate) {

        if (null != poolId) {
            val ipPool = poolRepository.findById(poolId);
            if (ipPool.isPresent() && getUsedCapacity(numberOfAddressToCreate) > (TOTAL_POOL_CAPACITY - ipPool.get().getUsedCapacity())) {
                logger.warn("Requested addresses cannot be created as pool size is exceeded and used capacity is{}",
                        ipPool.get().getUsedCapacity());
                return true;
            }
        }
        return false;
    }

    public IpAddressResponse createIpAddress(IpRequestCommand command) {
        val existingPool = poolRepository.findById(command.getPoolId());
        val ipPoolToSave = buildIpPool(command, existingPool);
        val totalUsedCapacity = findUsedPoolCapacity(existingPool) + getUsedCapacity(Long.valueOf(ipPoolToSave.getIpAddresses().size()));
        ipPoolToSave.setUsedCapacity(totalUsedCapacity);
        val response = poolRepository.save(ipPoolToSave);
        logger.info("Ip addresses are saved successfully");
        return IpAddressResponse.builder()
                .poolId(response.getId())
                .responseMessage("Pool addresses saved successfully")
                .ipAddresses(response.getIpAddresses())
                .build();
    }

    private IpPool buildIpPool(IpRequestCommand command, Optional<IpPool> existingPool) {
        return IpPool.builder()
                .id(command.getPoolId())
                .description(command.getDescription())
                .lowerBound(LOWER_BOUND)
                .upperBound(UPPER_BOUND)
                .totalCapacity(TOTAL_POOL_CAPACITY)
                .ipAddresses(buildAddresses(existingPool, command))
                .build();
    }

    private List<IpAddress> buildAddresses(Optional<IpPool> existingPool, IpRequestCommand command) {
        List<IpAddress> ipAddresses = new ArrayList<>();
        if (existingPool.isPresent()) {
            var usedCapacity = existingPool.get().getUsedCapacity();
            val addresses = existingPool.get().getIpAddresses();
            for (int i = addresses.size() + 1; i < command.getNumberOfAddressToCreate() && usedCapacity < existingPool.get().getUpperBound(); i++) {
                addAddresses(ipAddresses, i, command.getPoolId());
                usedCapacity = usedCapacity + getUsedCapacity(Long.valueOf(i));
            }
        } else {
            for (int i = 0; i < command.getNumberOfAddressToCreate(); i++) {
                addAddresses(ipAddresses, i, command.getPoolId());
            }
        }
        return ipAddresses;

    }

    private void addAddresses(List<IpAddress> ipAddresses, int i, Long poolId) {
        ipAddresses.add(
                IpAddress.builder()
                        .id(IpAddressId.builder().ipAddressId(Long.valueOf(i)).ipPoolId(poolId).build())
                        .value(getUsedCapacity(Long.valueOf(i)))
                        .build()
        );
    }

    private Long getUsedCapacity(Long numberOfAddressToCreate) {
        return numberOfAddressToCreate * 10;
    }

    private Long findUsedPoolCapacity(Optional<IpPool> pool) {
        return pool.isPresent() ? pool.get().getUsedCapacity() : Long.valueOf(0L);
    }
}
