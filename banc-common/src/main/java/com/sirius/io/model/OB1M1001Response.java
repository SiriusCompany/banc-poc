package com.sirius.io.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class OB1M1001Response implements Serializable {
    @JsonProperty("ok")
    private boolean ok;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    @Override
    public String toString() {
        return "Sob1m01001Response{" +
                "ok=" + ok +
                '}';
    }
}
