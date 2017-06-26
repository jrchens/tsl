package me.simple.sys.service;

import me.simple.domain.*;

import java.util.List;

public interface SysPermService {
    public int save(SysPerm sysPerm, CurrentUser currentUser);

    public int remove(SysPerm sysPerm, CurrentUser currentUser);

    public int update(SysPerm sysPerm, CurrentUser currentUser);

    public SysPerm get(SysPerm sysPerm, CurrentUser currentUser);

    public List<SysPerm> query(SysPerm sysPerm, Pageable pageable, CurrentUser currentUser);

    public List<SysPerm> getChildrenByPid(int pid);

    public TreeNode getTree();
}
