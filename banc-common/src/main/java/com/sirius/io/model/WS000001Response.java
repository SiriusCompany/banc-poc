package com.sirius.io.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class WS000001Response implements Serializable {    
    
    @JsonProperty("error")
    private String error;
    
    
    @JsonProperty("access_token")
    private String accessToken;
    
    
    @JsonProperty("expires_in")
    private Integer expiresIn;
    
    
    @JsonProperty("token_type")
    private String tokenType;
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
	
    @Override
    public String toString() {
        return "WS000001Response{"  +
		"error='" + error + '\''  +
		"accessToken='" + accessToken + '\''  +
		"expiresIn='" + expiresIn + '\''  +
		"tokenType='" + tokenType + '\''  +
                '}';
    }
}