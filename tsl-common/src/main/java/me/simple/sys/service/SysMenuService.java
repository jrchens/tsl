package me.simple.sys.service;

import me.simple.domain.CurrentUser;
import me.simple.domain.Pageable;
import me.simple.domain.SysMenu;
import me.simple.domain.TreeNode;

import java.util.List;

public interface SysMenuService {
    public int save(SysMenu sysMenu, CurrentUser currentUser);
    public int remove(SysMenu sysMenu, CurrentUser currentUser);
    public int update(SysMenu sysMenu, CurrentUser currentUser);
    public SysMenu get(SysMenu sysMenu, CurrentUser currentUser);
    public List<SysMenu> query(SysMenu sysMenu, Pageable pageable, CurrentUser currentUser);




    public List<SysMenu> queryAll();
    public SysMenu getRoot();

    public List<SysMenu> getChildrenByPid(int pid);

    public TreeNode getRootNode();
    public List<TreeNode> querySysMenuGroup();
    public List<TreeNode> queryChildrenByPid(int pid);
}
