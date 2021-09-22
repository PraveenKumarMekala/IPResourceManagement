package com.first.meridian.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "T_IP_ADDRESS")
public class IpAddress {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "totalCapacity")
    @Max(value = 250, message = "Max pool size can be 250")
    private Long totalCapacity;

    @Column(name = "usedCapacity")
    private Long usedCapacity;

    @Column(name = "lowerBound")
    @Min(value = 0, message = "Minimum size should be zero")
    private Long lowerBound;

    @Column(name = "upperBound")
    @Max(value = 250, message = "Maximum pools can be of size 250")
    private Long upperBound;

    @OneToMany
    @JoinColumn(name = "ipPoolId", referencedColumnName = "id", insertable = false, updatable = false)
    private List<IpPool> pools;

}
