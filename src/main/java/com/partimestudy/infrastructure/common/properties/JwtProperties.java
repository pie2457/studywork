package com.partimestudy.infrastructure.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record JwtProperties(
    String secretKey,
    long accessTokenExpirationTime
) {

}
