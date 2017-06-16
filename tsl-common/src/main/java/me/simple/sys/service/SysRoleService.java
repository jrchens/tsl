package me.simple.sys.service;

import me.simple.domain.CurrentUser;
import me.simple.domain.Pageable;
import me.simple.domain.SysRole;

import java.util.List;

public interface SysRoleService {
    public int save(SysRole sysRole, CurrentUser currentUser);
    public int remove(SysRole sysRole, CurrentUser currentUser);
    public int update(SysRole sysRole, CurrentUser currentUser);
    public SysRole get(SysRole sysRole, CurrentUser currentUser);
    public List<SysRole> query(SysRole sysRole, Pageable pageable, CurrentUser currentUser);

    public SysRole getByRolename(String rolename);
    public List<SysRole> queryAll();
    public SysRole getById(int id);
}
