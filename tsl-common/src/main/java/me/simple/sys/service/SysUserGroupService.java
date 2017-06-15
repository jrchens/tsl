package me.simple.sys.service;

import me.simple.domain.CurrentUser;
import me.simple.domain.Pageable;
import me.simple.domain.SysUserGroup;

import java.util.List;

public interface SysUserGroupService {
    public int save(SysUserGroup sysUserGroup, CurrentUser currentUser);
    public int remove(SysUserGroup sysUserGroup, CurrentUser currentUser);
    public int update(SysUserGroup sysUserGroup, CurrentUser currentUser);
    public SysUserGroup get(SysUserGroup sysUserGroup, CurrentUser currentUser);
    public List<SysUserGroup> query(SysUserGroup sysUserGroup, Pageable pageable, CurrentUser currentUser);


    public List<SysUserGroup> queryByUid(int uid);
    public List<SysUserGroup> queryByGid(int gid);
    public SysUserGroup getByUid(int uid);
    public SysUserGroup getByGid(int gid);
    public int removeByUidAndGid(int uid,int gid,CurrentUser currentUser);
}
