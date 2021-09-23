package com.first.meridian.command;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IpRequestCommand {

    private Long poolId;

    private Long numberOfAddressToCreate;

    private String description;
}
