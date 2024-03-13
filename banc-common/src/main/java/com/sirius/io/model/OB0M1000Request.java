package com.sirius.io.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class OB0M1000Request implements Serializable {
    @JsonProperty("id_user")
    @NotNull
    private String userID;

    @JsonProperty("transaction_amount")
    @NotNull
    private long transactionAmount;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    public long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @Override
    public String toString() {
        return "Sob0m01000Request{" +
                "userID='" + userID + '\'' +
                ", transactionAmount=" + transactionAmount +
                '}';
    }
}
