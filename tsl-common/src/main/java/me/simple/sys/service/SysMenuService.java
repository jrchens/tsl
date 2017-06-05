package me.simple.sys.service;

import me.simple.domain.CurrentUser;
import me.simple.domain.Pageable;
import me.simple.domain.SysMenu;

import java.util.List;

public interface SysMenuService {
    public int save(SysMenu sysMenu, CurrentUser currentUser);
    public int remove(SysMenu sysMenu, CurrentUser currentUser);
    public int update(SysMenu sysMenu, CurrentUser currentUser);
    public SysMenu get(SysMenu sysMenu, CurrentUser currentUser);
    public List<SysMenu> query(SysMenu sysMenu, Pageable pageable, CurrentUser currentUser);
}
