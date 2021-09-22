package com.first.meridian.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@Entity
@Table
public class IpPool {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "ipPoolId")
    private Long ipPoolId;

    @Column(name = "value")
    private Long value;

}
