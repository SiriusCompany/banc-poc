package com.sirius.io.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class WS000002Request implements Serializable {
    @JsonProperty("accountId")
    private String accountID;

    @JsonProperty("amount")
    private long amount;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BalanceAccumulationRequest {" +
                "accountID='" + accountID + '\'' +
                ", amount=" + amount +
                '}';
    }
}
