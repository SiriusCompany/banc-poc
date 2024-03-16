package com.sirius.io.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OB9M1001Response implements Serializable {    
    
    @JsonProperty("id_account")
    private String idAccount;
    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }
	
    @Override
    public String toString() {
        return "OB9M1001Response{"  +
		"idAccount='" + idAccount + '\''  +
                '}';
    }
}