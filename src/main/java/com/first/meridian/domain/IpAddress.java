package com.first.meridian.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "IP_ADDRESS")
public class IpAddress {

    @EmbeddedId
    private IpAddressId id;

    @Column(name = "value")
    private Long value;

}
