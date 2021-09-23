package com.first.meridian.repository;

import com.first.meridian.domain.IpPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpPoolRepository extends JpaRepository<IpPool, Long> {
}
