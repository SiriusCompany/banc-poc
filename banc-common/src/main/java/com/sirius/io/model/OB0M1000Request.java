package com.sirius.io.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OB0M1000Request implements Serializable {    
    @NotNull(message = "id_user cannot be null")
    @JsonProperty("id_user")
    private String idUser;
    
    @NotNull(message = "transaction_amount cannot be null")
    @JsonProperty("transaction_amount")
    private Long transactionAmount;
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
	
    @Override
    public String toString() {
        return "OB0M1000Request{"  +
		"idUser='" + idUser + '\''  +
		"transactionAmount='" + transactionAmount + '\''  +
                '}';
    }
}