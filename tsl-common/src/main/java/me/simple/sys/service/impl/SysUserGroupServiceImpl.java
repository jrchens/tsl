package me.simple.sys.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import me.simple.domain.CurrentUser;
import me.simple.domain.Pageable;
import me.simple.domain.SysUserGroup;
import me.simple.sys.service.SysUserGroupService;
import me.simple.util.SQLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
public class SysUserGroupServiceImpl implements SysUserGroupService {

    private static final Logger logger = LoggerFactory.getLogger(SysUserGroupService.class);
    public static final String tableName = "sys_user_group";
    private static final String generatedKeyName = "id";
    private static final List<String> insertColumns = Lists.newArrayList("uid", "gid", "srt", "cruser", "crtime");
    private static final List<String> updateColumns = Lists.newArrayList("srt", "mduser", "mdtime");

    private SimpleJdbcInsert simpleJdbcInsert;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
    public int save(SysUserGroup sysUserGroup, CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysUserGroup.setCruser(user);
        sysUserGroup.setCrtime(time);

        Integer srt = jdbcTemplate.queryForObject("SELECT MAX(srt) FROM " + tableName + " WHERE deleted = 0 AND disabled = 0 AND uid = ? ", Integer.class, sysUserGroup.getUid());
        if (srt == null) {
            srt = 10;
        } else {
            srt += 10;
        }
        sysUserGroup.setSrt(srt);


        return simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(sysUserGroup)).intValue();
    }

    @Override
    public int remove(SysUserGroup sysUserGroup, CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysUserGroup.setMduser(user);
        sysUserGroup.setMdtime(time);

        int aff = 0;
        String ids = sysUserGroup.getIds();
        List<String> idlist = Splitter.on(",").splitToList(ids);
        for (String id : idlist) {
            sysUserGroup.setId(Integer.parseInt(id));
            aff += namedParameterJdbcTemplate.update(SQLUtil.generateRemoveSql(tableName, generatedKeyName), new BeanPropertySqlParameterSource(sysUserGroup));
        }

        return aff;
    }

    @Override
    public int update(SysUserGroup sysUserGroup, CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysUserGroup.setMduser(user);
        sysUserGroup.setMdtime(time);
        return namedParameterJdbcTemplate.update(SQLUtil.generateUpdateSql(tableName, updateColumns, generatedKeyName), new BeanPropertySqlParameterSource(sysUserGroup));
    }

    @Override
    @Transactional(readOnly = true)
    public SysUserGroup get(SysUserGroup sysUserGroup, CurrentUser currentUser) {
        return namedParameterJdbcTemplate.queryForObject(SQLUtil.generateGetSql(tableName, generatedKeyName), new BeanPropertySqlParameterSource(sysUserGroup), new BeanPropertyRowMapper<SysUserGroup>(SysUserGroup.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysUserGroup> query(SysUserGroup sysUserGroup, Pageable pageable, CurrentUser currentUser) {
        List<Object> args = Lists.newArrayList();
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM ").append(tableName).append(" ");
        buffer.append("WHERE deleted = 0 ");

        int total = jdbcTemplate.queryForObject(SQLUtil.generateCountSQL(buffer.toString()), args.toArray(), Integer.class);
        pageable.setTotal(total);

        SQLUtil.sortingAndPaging(buffer, pageable);

        return jdbcTemplate.query(buffer.toString(), args.toArray(), new BeanPropertyRowMapper<SysUserGroup>(SysUserGroup.class));
    }


    @Override
    @Transactional(readOnly = true)
    public List<SysUserGroup> queryByUid(int uid) {
        List<Object> args = Lists.newArrayList();
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM ").append(tableName).append(" ");
        buffer.append("WHERE deleted = 0 ");

        buffer.append("AND disabled = ? ");
        args.add(false);

        buffer.append("AND uid = ? ORDER BY srt ASC ");
        args.add(uid);

        return jdbcTemplate.query(buffer.toString(), args.toArray(), new BeanPropertyRowMapper<SysUserGroup>(SysUserGroup.class));
    }


    @Override
    @Transactional(readOnly = true)
    public List<SysUserGroup> queryByGid(int gid) {
        List<Object> args = Lists.newArrayList();
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM ").append(tableName).append(" ");
        buffer.append("WHERE deleted = 0 ");

        buffer.append("AND disabled = ? ");
        args.add(false);

        buffer.append("AND gid = ? ORDER BY srt ASC ");
        args.add(gid);

        return jdbcTemplate.query(buffer.toString(), args.toArray(), new BeanPropertyRowMapper<SysUserGroup>(SysUserGroup.class));
    }

    @Override
    @Transactional(readOnly = true)
    public SysUserGroup getByUid(int uid) {
        List<SysUserGroup> list = queryByUid(uid);
        if (list.isEmpty()) return new SysUserGroup();
        else return list.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public SysUserGroup getByGid(int gid) {
        List<SysUserGroup> list = queryByGid(gid);
        if (list.isEmpty()) return new SysUserGroup();
        else return list.get(0);
    }

    @Override
    public int removeByUidAndGid(int uid, int gid, CurrentUser currentUser) {

        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        List<String> uniqueKeys = Lists.newArrayList("uid", "gid");
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("mduser", user);
        paramSource.addValue("mdtime", time);
        paramSource.addValue("uid", uid);
        paramSource.addValue("gid", gid);

        return namedParameterJdbcTemplate.update(SQLUtil.generateRemoveSql(tableName, uniqueKeys), paramSource);

    }
}
