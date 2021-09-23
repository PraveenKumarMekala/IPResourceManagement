package com.first.meridian.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "IP_ADDRESS")
public class IpAddress {

    @EmbeddedId
    private IpAddressId id;

    @Column(name = "value")
    private Long value;

}
