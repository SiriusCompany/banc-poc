package com.sirius.io.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class WS000002Response implements Serializable {
    @JsonProperty("error")
    private String error;

    @JsonProperty("id")
    private String id;

    @JsonProperty("balance")
    private long balance;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BalanceAccumulationResponse {" +
                "error='" + error + '\'' +
                ", id='" + id + '\'' +
                ", balance=" + balance +
                '}';
    }
}
