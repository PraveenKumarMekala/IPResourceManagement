package com.first.meridian.repository;

import com.first.meridian.domain.IpAddress;
import com.first.meridian.domain.IpAddressId;
import com.first.meridian.domain.IpPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpAddressRepository extends JpaRepository<IpAddress, IpAddressId> {

    List<IpAddress> findByIdIpPoolId(Long ipPoolId);
}
