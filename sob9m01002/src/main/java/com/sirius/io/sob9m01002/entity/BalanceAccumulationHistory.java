package com.sirius.io.sob9m01002.entity;


import java.io.Serializable;
import java.sql.Timestamp;


public class BalanceAccumulationHistory implements Serializable {
    //fields
    private String transactionId;
    private String idUser;
    private String idAccount;
    private Long transactionAmount;
    private Long balance;
    private String retCode;
    private String retMessage;
    private Timestamp createTime;

    //get set
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }
    public Long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
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
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    @Override
    public String toString() {
        return "BalanceAccumulationHistory{" +
		"transactionId='" + transactionId + '\'' +
		"idUser='" + idUser + '\'' +
		"idAccount='" + idAccount + '\'' +
		"transactionAmount='" + transactionAmount + '\'' +
		"balance='" + balance + '\'' +
		"retCode='" + retCode + '\'' +
		"retMessage='" + retMessage + '\'' +
		"createTime='" + createTime + '\''  +
                '}';
    }
}