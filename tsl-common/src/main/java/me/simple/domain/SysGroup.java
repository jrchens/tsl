package me.simple.domain;

import me.simple.validator.group.Remove;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.sql.Timestamp;

public class SysGroup implements java.io.Serializable {

    private int id;
    private String groupname;
    private String viewname;

    private boolean disabled;
    private boolean deleted;
    private String cruser;
    private Timestamp crtime;
    private String mduser;
    private Timestamp mdtime;

    // ============================================
    @Length(min = 1,max = 200,groups = {Remove.class})
    private String ids;

    // ============================================
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getViewname() {
        return viewname;
    }

    public void setViewname(String viewname) {
        this.viewname = viewname;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
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