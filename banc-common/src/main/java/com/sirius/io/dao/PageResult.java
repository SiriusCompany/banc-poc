package com.sirius.io.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class PageResult<T extends Serializable> implements Serializable {
    @JsonProperty("data_list")
    private final List<T> dataList;

    @JsonProperty("total_count")
    private final int totalCount;

    @JsonProperty("current_page")
    private final int currentPage;

    @JsonProperty("page_size")
    private final int pageSize;


    public PageResult(List<T> dataList, int totalCount, int currentPage, int pageSize) {
        this.dataList = dataList;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "dataList=" + dataList +
                ", totalCount=" + totalCount +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}