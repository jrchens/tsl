package me.simple.domain;

/**
 * Created by chensheng on 17/5/24
 */
public class Pageable implements java.io.Serializable {
    private static final long serialVersionUID = 727399751394881960L;
    public Pageable() {
        super();
    }

    public Pageable(int page, int rows) {
        super();
        this.page = page;
        this.rows = rows;
    }

    public Pageable(boolean pable, boolean sable) {
        super();
        this.pable = pable;
        this.sable = sable;
    }



    private int page = 1;
    private int rows = 10;
    private String sort;
    private String order;
    private int total;
    private boolean pable = true;
    private boolean sable = true;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setSortOrder(String sort, String order) {
        this.sort = sort;
        this.order = order;
    }

    public boolean isPable() {
        return pable;
    }

    public void setPable(boolean pable) {
        this.pable = pable;
    }

    public boolean isSable() {
        return sable;
    }

    public void setSable(boolean sable) {
        this.sable = sable;
    }
}
