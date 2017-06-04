package me.simple.sys.service;

import me.simple.domain.CurrentUser;
import me.simple.domain.Pageable;
import me.simple.domain.SysUser;

import java.util.List;

/**
 * Created by chensheng on 17/5/28.
 */
public interface SysUserService {
    public int save(SysUser sysUser, CurrentUser currentUser);
    public int remove(SysUser sysUser, CurrentUser currentUser);
    public int update(SysUser sysUser, CurrentUser currentUser);
    public SysUser get(SysUser sysUser, CurrentUser currentUser);
    public List<SysUser> query(SysUser sysUser, Pageable pageable, CurrentUser currentUser);


    public SysUser getByUsername(SysUser sysUser, CurrentUser currentUser);
}
