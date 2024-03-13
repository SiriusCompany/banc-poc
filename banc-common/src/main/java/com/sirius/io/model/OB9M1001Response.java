package com.sirius.io.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class OB9M1001Response implements Serializable {
    @JsonProperty("id_user")
    private String idUser;

    @JsonProperty("id_account")
    private String idAccount;

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
}
