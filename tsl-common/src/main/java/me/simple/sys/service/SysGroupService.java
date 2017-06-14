package me.simple.sys.service;

import me.simple.domain.CurrentUser;
import me.simple.domain.Pageable;
import me.simple.domain.SysGroup;

import java.util.List;

public interface SysGroupService {
    public int save(SysGroup sysGroup, CurrentUser currentUser);
    public int remove(SysGroup sysGroup, CurrentUser currentUser);
    public int update(SysGroup sysGroup, CurrentUser currentUser);
    public SysGroup get(SysGroup sysGroup, CurrentUser currentUser);
    public List<SysGroup> query(SysGroup sysGroup, Pageable pageable, CurrentUser currentUser);
    public List<SysGroup> queryAll(boolean filterDisabled);


    public SysGroup getByGroupname(String groupname);
}
