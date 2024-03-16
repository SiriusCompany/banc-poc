package com.sirius.io.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OB1M1001Request implements Serializable {    
    
    @JsonProperty("amount")
    private Long amount;
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
	
    @Override
    public String toString() {
        return "OB1M1001Request{"  +
		"amount='" + amount + '\''  +
                '}';
    }
}