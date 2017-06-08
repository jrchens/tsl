package me.simple.domain;

import me.simple.validator.group.Edit;
import me.simple.validator.group.Remove;
import me.simple.validator.group.Save;
import me.simple.validator.group.Update;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import java.sql.Timestamp;

public class SysUser implements java.io.Serializable {
    @Range(min=1,max=Integer.MAX_VALUE,groups = {Edit.class, Update.class})
    private int id;

    @Length(min = 4, max = 32, groups = {Save.class})
    private String username;

    @Length(min = 2, max = 32, groups = {Save.class, Update.class})
    private String viewname;

    @Length(min = 4, max = 32, groups = {Save.class})
    private String password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getViewname() {
        return viewname;
    }

    public void setViewname(String viewname) {
        this.viewname = viewname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}