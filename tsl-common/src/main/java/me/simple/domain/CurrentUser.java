package me.simple.domain;

import java.util.List;

/**
 * Created by chensheng on 17/5/28.
 */
public class CurrentUser {
    private int id;
    private String username;
    private String viewname;
    private SysGroup sysGroup;
    private List<SysRole> sysRoleList;
    private List<SysGroup> sysGroupList;

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

    public SysGroup getSysGroup() {
        return sysGroup;
    }

    public void setSysGroup(SysGroup sysGroup) {
        this.sysGroup = sysGroup;
    }

    public List<SysRole> getSysRoleList() {
        return sysRoleList;
    }

    public void setSysRoleList(List<SysRole> sysRoleList) {
        this.sysRoleList = sysRoleList;
    }

    public List<SysGroup> getSysGroupList() {
        return sysGroupList;
    }

    public void setSysGroupList(List<SysGroup> sysGroupList) {
        this.sysGroupList = sysGroupList;
    }
}
