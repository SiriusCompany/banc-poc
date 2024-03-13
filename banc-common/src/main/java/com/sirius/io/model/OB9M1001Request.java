package com.sirius.io.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class OB9M1001Request implements Serializable {
    @JsonProperty("id_user")
    private String userID;

    @JsonProperty("id_account")
    private String accountID;

    @JsonProperty("page")
    private int page;

    @JsonProperty("page_size")
    private int pageSize;

    @JsonProperty("page_find_condition")
    private String pageFindCondition;

    @JsonProperty("page_find_order_by")
    private String pageFindOrderBy;

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


    public String getPageFindCondition() {
        return pageFindCondition;
    }

    public void setPageFindCondition(String pageFindCondition) {
        this.pageFindCondition = pageFindCondition;
    }

    public String getPageFindOrderBy() {
        return pageFindOrderBy;
    }

    public void setPageFindOrderBy(String pageFindOrderBy) {
        this.pageFindOrderBy = pageFindOrderBy;
    }

    @Override
    public String toString() {
        return "Sob9m01001Request{" +
                "userID='" + userID + '\'' +
                ", accountID='" + accountID + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", pageFindCondition='" + pageFindCondition + '\'' +
                ", pageFindOrderBy='" + pageFindOrderBy + '\'' +
                '}';
    }
}
