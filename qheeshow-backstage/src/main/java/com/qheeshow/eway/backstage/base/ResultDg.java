package com.qheeshow.eway.backstage.base;

public class ResultDg<T> {

    private int total;
    private T rows;

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }

    public T getRows() {
        return rows;
    }
}
