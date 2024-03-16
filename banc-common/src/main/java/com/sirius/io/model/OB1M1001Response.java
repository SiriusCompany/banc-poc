package com.sirius.io.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OB1M1001Response implements Serializable {    
    
    @JsonProperty("ok")
    private Boolean ok;
    public Boolean isOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }
	
    @Override
    public String toString() {
        return "OB1M1001Response{"  +
		"ok='" + ok + '\''  +
                '}';
    }
}