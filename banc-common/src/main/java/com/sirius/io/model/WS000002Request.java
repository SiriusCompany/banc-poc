package com.sirius.io.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class WS000002Request implements Serializable {    
    
    @JsonProperty("accountId")
    private String accountId;
    
    
    @JsonProperty("amount")
    private Long amount;
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
	
    @Override
    public String toString() {
        return "WS000002Request{"  +
		"accountId='" + accountId + '\''  +
		"amount='" + amount + '\''  +
                '}';
    }
}