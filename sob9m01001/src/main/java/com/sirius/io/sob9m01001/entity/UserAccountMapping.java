package com.sirius.io.sob9m01001.entity;


import java.io.Serializable;

public class UserAccountMapping implements Serializable {

    private String idUser;

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

    @Override
    public String toString() {
        return "UserAccountMapping{" +
                "idUser='" + idUser + '\'' +
                ", idAccount='" + idAccount + '\'' +
                '}';
    }
}
