package me.simple.sys.service.impl;

import com.google.common.collect.Lists;
import me.simple.domain.CurrentUser;
import me.simple.domain.Pageable;
import me.simple.domain.SysUser;
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
    private static final List<String> insertColumns = Lists.newArrayList("username","viewname","password","cruser","crtime");
    private static final List<String> updateColumns = Lists.newArrayList("viewname","password","mduser","mdtime");

    private SimpleJdbcInsert simpleJdbcInsert;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
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
        return simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(sysUser)).intValue();
    }

    @Override
    public int remove(SysUser sysUser, CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysUser.setMduser(user);
        sysUser.setMdtime(time);
        return namedParameterJdbcTemplate.update(SQLUtil.generateRemoveSql(tableName,generatedKeyName),new BeanPropertySqlParameterSource(sysUser));
    }

    @Override
    public int update(SysUser sysUser, CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysUser.setMduser(user);
        sysUser.setMdtime(time);
        return namedParameterJdbcTemplate.update(SQLUtil.generateUpdateSql(tableName,updateColumns,generatedKeyName),new BeanPropertySqlParameterSource(sysUser));
    }

    @Override
    @Transactional(readOnly = true)
    public SysUser get(SysUser sysUser, CurrentUser currentUser) {
        return namedParameterJdbcTemplate.queryForObject(SQLUtil.generateGetSql(tableName,generatedKeyName),new BeanPropertySqlParameterSource(sysUser),new BeanPropertyRowMapper<SysUser>(SysUser.class));
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

        if(StringUtils.hasText(viewname)) {
            buffer.append("AND viewname LIKE ? ");
            args.add(SQLUtil.like(viewname));
        }
        if(StringUtils.hasText(username)) {
            buffer.append("AND username LIKE ? ");
            args.add(SQLUtil.like(username));
        }

        int total = jdbcTemplate.queryForObject(SQLUtil.generateCountSQL(buffer.toString()),args.toArray(),Integer.class);
        pageable.setTotal(total);

        SQLUtil.sortingAndPaging(buffer,pageable);

        return jdbcTemplate.query(buffer.toString(),args.toArray(),new BeanPropertyRowMapper<SysUser>(SysUser.class));
    }

    @Override
    @Transactional(readOnly = true)
    public SysUser getByUsername(String username) {
        try {
            SysUser sysUser = new SysUser();
            sysUser.setUsername(username);
            return namedParameterJdbcTemplate.queryForObject(SQLUtil.generateGetSql(tableName, "username"), new BeanPropertySqlParameterSource(sysUser), new BeanPropertyRowMapper<SysUser>(SysUser.class));
        } catch (DataAccessException e) {
            // username not exists
            return null;
        }
    }

}
