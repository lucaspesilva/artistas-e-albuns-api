package br.com.dekagames.artistasealbunsapi.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("jwt")
public class SecurityConstants {
    private String secret;
    private long expirationTime;
    private String tokenPrefix;
    private String headerString;
    private String[] swaggerUrls;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public String getHeaderString() {
        return headerString;
    }

    public void setHeaderString(String headerString) {
        this.headerString = headerString;
    }

    public String[] getSwaggerUrls() {
        return swaggerUrls;
    }

    public void setSwaggerUrls(String[] swaggerUrls) {
        this.swaggerUrls = swaggerUrls;
    }
}
