package com.first.meridian.repository;

import com.first.meridian.domain.IpAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpAddressRepository extends JpaRepository<IpAddress, Long> {
}
