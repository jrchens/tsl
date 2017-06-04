package me.simple.domain;

import me.simple.validator.group.Edit;
import me.simple.validator.group.Remove;
import me.simple.validator.group.Update;
import org.hibernate.validator.constraints.Range;

import java.sql.Timestamp;

/**
 * Created by chensheng on 17/5/28.
 */
public class Base implements java.io.Serializable {

    private String rowid;
    private String rowids;

    private boolean disabled;
    private boolean deleted;
    private String cruser;
    private Timestamp crtime;
    private String mduser;
    private Timestamp mdtime;


    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public String getRowids() {
        return rowids;
    }

    public void setRowids(String rowids) {
        this.rowids = rowids;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getCruser() {
        return cruser;
    }

    public void setCruser(String cruser) {
        this.cruser = cruser;
    }

    public Timestamp getCrtime() {
        return crtime;
    }

    public void setCrtime(Timestamp crtime) {
        this.crtime = crtime;
    }

    public String getMduser() {
        return mduser;
    }

    public void setMduser(String mduser) {
        this.mduser = mduser;
    }

    public Timestamp getMdtime() {
        return mdtime;
    }

    public void setMdtime(Timestamp mdtime) {
        this.mdtime = mdtime;
    }
}
