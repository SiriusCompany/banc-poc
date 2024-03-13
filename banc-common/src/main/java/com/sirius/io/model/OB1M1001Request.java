package com.sirius.io.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class OB1M1001Request implements Serializable {
    @JsonProperty("amount")
    private long amount;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Sob1m01001Request{" +
                "amount=" + amount +
                '}';
    }
}
