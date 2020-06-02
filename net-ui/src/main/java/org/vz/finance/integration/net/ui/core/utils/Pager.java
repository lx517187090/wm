package org.vz.finance.integration.net.ui.core.utils;


import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class Pager<T> extends Object implements Serializable {
    private int total;
    private int size = 10;

    private int pages;

    private int current = 1;
    private static final long serialVersionUID = 1L;

    private List<T> records = Collections.emptyList();

    private Map<String, Object> condition;

    public int getTotal() {
        return this.total;
    }


    public void setTotal(int total) {
        this.total = total;
    }


    public int getSize() {
        return this.size;
    }


    public void setSize(int size) {
        this.size = size;
    }


    public int getPages() {
        return this.pages;
    }


    public void setPages(int pages) {
        this.pages = pages;
    }


    public int getCurrent() {
        return this.current;
    }


    public void setCurrent(int current) {
        this.current = current;
    }


    public List<T> getRecords() {
        return this.records;
    }


    public void setRecords(List<T> records) {
        this.records = records;
    }


    public Map<String, Object> getCondition() {
        return this.condition;
    }


    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }
}
