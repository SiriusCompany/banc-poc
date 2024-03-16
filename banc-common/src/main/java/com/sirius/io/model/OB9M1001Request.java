package com.sirius.io.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OB9M1001Request implements Serializable {    
    
    @JsonProperty("id_user")
    private String idUser;
    
    
    @JsonProperty("page")
    private Integer page;
    
    
    @JsonProperty("page_size")
    private Integer pageSize;
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
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
        return "OB9M1001Request{"  +
		"idUser='" + idUser + '\''  +
		"page='" + page + '\''  +
		"pageSize='" + pageSize + '\''  +
                '}';
    }
}