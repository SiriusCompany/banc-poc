package com.sirius.io.sob9m01002.entity;


import java.io.Serializable;
import java.util.Date;

public class TransactionHistory implements Serializable {
    private String transactionID;

    private String userID;

    private String accountID;

    private long transactionAmount;

    private String balance;

    private String retCode;

    private String retMessage;

    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    @Override
    public String toString() {
        return "TransactionHistory{" +
                "transactionID='" + transactionID + '\'' +
                ", userID='" + userID + '\'' +
                ", accountID='" + accountID + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", balance=" + balance +
                ", retCode='" + retCode + '\'' +
                ", retMessage='" + retMessage + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
