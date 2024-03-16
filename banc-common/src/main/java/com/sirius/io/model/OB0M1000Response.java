package com.sirius.io.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OB0M1000Response implements Serializable {    
    
    @JsonProperty("id_account")
    private String idAccount;
    
    
    @JsonProperty("balance")
    private Long balance;
    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
	
    @Override
    public String toString() {
        return "OB0M1000Response{"  +
		"idAccount='" + idAccount + '\''  +
		"balance='" + balance + '\''  +
                '}';
    }
}