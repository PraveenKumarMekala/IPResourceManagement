package com.first.meridian.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class IpAddressId implements Serializable {

    @Column(name = "id")
    private Long ipAddressId;

    @Column(name = "ipPoolId")
    private Long ipPoolId;
}
