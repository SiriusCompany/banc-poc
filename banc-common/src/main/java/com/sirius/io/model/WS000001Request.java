package com.sirius.io.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class WS000001Request implements Serializable {    
    
    @JsonProperty("content")
    private String content;
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
	
    @Override
    public String toString() {
        return "WS000001Request{"  +
		"content='" + content + '\''  +
                '}';
    }
}