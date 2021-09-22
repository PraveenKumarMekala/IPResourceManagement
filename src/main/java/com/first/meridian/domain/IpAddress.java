package com.first.meridian.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private Long totalCapacity;

    @Column(name = "usedCapacity")
    private Long usedCapacity;

    @Column(name = "lowerBound")
    private Long lowerBound;

    @Column(name = "upperBound")
    private Long upperBound;

    @OneToMany
    @JoinColumn(name = "ipPoolId", referencedColumnName = "id")
    private List<IpPool> pools;

}
