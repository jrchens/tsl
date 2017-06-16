package me.simple.sys.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import me.simple.domain.*;
import me.simple.sys.service.SysGroupService;
import me.simple.sys.service.SysRoleService;
import me.simple.sys.service.SysUserGroupService;
import me.simple.sys.service.SysUserService;
import me.simple.util.SQLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    private static final Logger logger = LoggerFactory.getLogger(SysUserService.class);
    public static final String tableName = "sys_user";
    private static final String generatedKeyName = "id";
    private static final List<String> insertColumns = Lists.newArrayList("username", "viewname", "password", "cruser", "crtime");
    private static final List<String> updateColumns = Lists.newArrayList("viewname", "password", "mduser", "mdtime");

    private SimpleJdbcInsert simpleJdbcInsert;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private SysUserGroupService sysUserGroupService;
    @Autowired
    private SysGroupService sysGroupService;
    @Autowired
    private SysRoleService sysRoleService;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
        this.simpleJdbcInsert.setTableName(tableName);
        this.simpleJdbcInsert.setGeneratedKeyName(generatedKeyName);
        this.simpleJdbcInsert.setColumnNames(insertColumns);

        this.jdbcTemplate = new JdbcTemplate(dataSource);

        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public int save(SysUser sysUser, CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysUser.setCruser(user);
        sysUser.setCrtime(time);
        int id = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(sysUser)).intValue();

        // sysUserGroupService.save(new SysUserGroup(id,sysUser.getGid(),1),currentUser);

        return id;
    }

    @Override
    public int remove(SysUser sysUser, CurrentUser currentUser) {
        int aff = 0;
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysUser.setMduser(user);
        sysUser.setMdtime(time);

        String ids = sysUser.getIds();
        List<String> idlist = Splitter.on(",").splitToList(ids);
        for (String id : idlist) {
            sysUser.setId(Integer.parseInt(id));
            aff += namedParameterJdbcTemplate.update(SQLUtil.generateRemoveSql(tableName, generatedKeyName), new BeanPropertySqlParameterSource(sysUser));
        }
        return aff;
    }

    @Override
    public int update(SysUser sysUser, CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysUser.setMduser(user);
        sysUser.setMdtime(time);

        // password empty process
        String password = sysUser.getPassword();
        if (!StringUtils.hasText(password)) {
            SysUser clone = get(sysUser, currentUser);
            sysUser.setPassword(clone.getPassword());
        }

//        int uid = sysUser.getId();
//        int gid = sysUser.getGid();
        // remove the sys_user_group (uid = ${uid} AND gid = ${gid})
//        sysUserGroupService.removeByUidAndGid(uid,gid,currentUser);

        return namedParameterJdbcTemplate.update(SQLUtil.generateUpdateSql(tableName, updateColumns, generatedKeyName), new BeanPropertySqlParameterSource(sysUser));
    }

    @Override
    @Transactional(readOnly = true)
    public SysUser get(SysUser sysUser, CurrentUser currentUser) {
        SysUser user = namedParameterJdbcTemplate.queryForObject(SQLUtil.generateGetSql(tableName, generatedKeyName), new BeanPropertySqlParameterSource(sysUser), new BeanPropertyRowMapper<SysUser>(SysUser.class));
//        user.setGroup(sysGroupService.getById(user.getGid()));
//        user.setRole(sysRoleService.getById(user.getRid()));
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysUser> query(SysUser sysUser, Pageable pageable, CurrentUser currentUser) {
        String viewname = sysUser.getViewname();
        String username = sysUser.getUsername();

        List<Object> args = Lists.newArrayList();
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM ").append(tableName).append(" ");
        buffer.append("WHERE deleted = 0 ");

        if (StringUtils.hasText(viewname)) {
            buffer.append("AND viewname LIKE ? ");
            args.add(SQLUtil.like(viewname));
        }
        if (StringUtils.hasText(username)) {
            buffer.append("AND username LIKE ? ");
            args.add(SQLUtil.like(username));
        }

        int total = jdbcTemplate.queryForObject(SQLUtil.generateCountSQL(buffer.toString()), args.toArray(), Integer.class);
        pageable.setTotal(total);

        SQLUtil.sortingAndPaging(buffer, pageable);

        List<SysUser> users = jdbcTemplate.query(buffer.toString(), args.toArray(), new BeanPropertyRowMapper<SysUser>(SysUser.class));
//        for (SysUser user : users
//                ) {
//            user.setGroup(sysGroupService.getById(user.getGid()));
//            user.setRole(sysRoleService.getById(user.getRid()));
//        }
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public SysUser getByUsername(String username) {
        try {
            SysUser sysUser = new SysUser();
            sysUser.setUsername(username);
            return namedParameterJdbcTemplate.queryForObject(SQLUtil.generateGetAllSql(tableName, "username"), new BeanPropertySqlParameterSource(sysUser), new BeanPropertyRowMapper<SysUser>(SysUser.class));
        } catch (DataAccessException e) {
            // username not exists
            return null;
        }
    }

}
