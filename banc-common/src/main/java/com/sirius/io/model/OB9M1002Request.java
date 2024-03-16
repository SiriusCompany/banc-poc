package com.sirius.io.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OB9M1002Request implements Serializable {    
    @NotNull(message = "transaction_id cannot be null")
    @JsonProperty("transaction_id")
    private String transactionId;
    
    
    @JsonProperty("id_user")
    private String idUser;
    
    
    @JsonProperty("id_account")
    private String idAccount;
    
    
    @JsonProperty("transaction_amount")
    private Long transactionAmount;
    
    
    @JsonProperty("balance")
    private Long balance;
    
    
    @JsonProperty("ret_code")
    private String retCode;
    
    
    @JsonProperty("ret_message")
    private String retMessage;
    
    
    @JsonProperty("page")
    private Integer page;
    
    
    @JsonProperty("page_size")
    private Integer pageSize;
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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
	
    @Override
    public String toString() {
        return "OB9M1002Request{"  +
		"transactionId='" + transactionId + '\''  +
		"idUser='" + idUser + '\''  +
		"idAccount='" + idAccount + '\''  +
		"transactionAmount='" + transactionAmount + '\''  +
		"balance='" + balance + '\''  +
		"retCode='" + retCode + '\''  +
		"retMessage='" + retMessage + '\''  +
		"page='" + page + '\''  +
		"pageSize='" + pageSize + '\''  +
                '}';
    }
}