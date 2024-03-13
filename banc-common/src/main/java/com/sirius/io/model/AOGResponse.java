package com.sirius.io.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

public class AOGResponse implements Serializable {
    @JsonProperty("headers")
    private Map<String, String[]> headers;
    @JsonProperty("body")
    private String body;

    public Map<String, String[]> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String[]> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Aog{" +
                "headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}
