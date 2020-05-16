package com.oauth2.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "oauth2.security")
public class OAuth2Properties {

    private String jwtSigningKey = "jwtSigningKey";

}
