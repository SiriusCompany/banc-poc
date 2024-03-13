package com.sirius.io.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class OB9M1002Request implements Serializable {
    @JsonProperty("transaction_id")
    private String transactionID;

    @JsonProperty("id_user")
    private String userID;

    @JsonProperty("id_account")
    private String accountID;

    @JsonProperty("transaction_amount")
    private long transactionAmount;

    @JsonProperty("balance")
    private String balance;

    @JsonProperty("ret_code")
    private String retCode;

    @JsonProperty("ret_message")
    private String retMessage;

    @JsonProperty("page")
    private int page;

    @JsonProperty("page_size")
    private int pageSize;

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }


    public long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }


    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Sob9m01002Request{" +
                "transactionID='" + transactionID + '\'' +
                ", userID='" + userID + '\'' +
                ", accountID='" + accountID + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", balance='" + balance + '\'' +
                ", retCode='" + retCode + '\'' +
                ", retMessage='" + retMessage + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
