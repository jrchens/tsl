package me.simple.sys.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import me.simple.domain.CurrentUser;
import me.simple.domain.Pageable;
import me.simple.domain.SysGroup;
import me.simple.domain.SysUser;
import me.simple.sys.service.SysGroupService;
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
public class SysGroupServiceImpl implements SysGroupService {

    private static final Logger logger = LoggerFactory.getLogger(SysGroupService.class);
    public static final String tableName = "sys_group";
    private static final String generatedKeyName = "id";
    private static final List<String> insertColumns = Lists.newArrayList("groupname", "viewname", "cruser", "crtime");
    private static final List<String> updateColumns = Lists.newArrayList("viewname", "mduser", "mdtime");

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
    public int save(SysGroup sysGroup, CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysGroup.setCruser(user);
        sysGroup.setCrtime(time);
        return simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(sysGroup)).intValue();
    }

    @Override
    public int remove(SysGroup sysGroup, CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysGroup.setMduser(user);
        sysGroup.setMdtime(time);

        int aff = 0;
        String ids = sysGroup.getIds();
        List<String> idlist = Splitter.on(",").splitToList(ids);
        for (String id : idlist) {
            sysGroup.setId(Integer.parseInt(id));
            aff += namedParameterJdbcTemplate.update(SQLUtil.generateRemoveSql(tableName, generatedKeyName), new BeanPropertySqlParameterSource(sysGroup));
        }
        return aff;
    }

    @Override
    public int update(SysGroup sysGroup, CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysGroup.setMduser(user);
        sysGroup.setMdtime(time);
        return namedParameterJdbcTemplate.update(SQLUtil.generateUpdateSql(tableName, updateColumns, generatedKeyName), new BeanPropertySqlParameterSource(sysGroup));
    }

    @Override
    @Transactional(readOnly = true)
    public SysGroup get(SysGroup sysGroup, CurrentUser currentUser) {
        return namedParameterJdbcTemplate.queryForObject(SQLUtil.generateGetSql(tableName, generatedKeyName), new BeanPropertySqlParameterSource(sysGroup), new BeanPropertyRowMapper<SysGroup>(SysGroup.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysGroup> query(SysGroup sysGroup, Pageable pageable, CurrentUser currentUser) {
        List<Object> args = Lists.newArrayList();
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM ").append(tableName).append(" ");
        buffer.append("WHERE deleted = 0 ");

        int total = jdbcTemplate.queryForObject(SQLUtil.generateCountSQL(buffer.toString()), args.toArray(), Integer.class);
        pageable.setTotal(total);

        SQLUtil.sortingAndPaging(buffer, pageable);

        return jdbcTemplate.query(buffer.toString(), args.toArray(), new BeanPropertyRowMapper<SysGroup>(SysGroup.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysGroup> queryAll() {

        List<Object> args = Lists.newArrayList();
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM ").append(tableName).append(" ");
        buffer.append("WHERE deleted = 0 ");

        buffer.append("AND disabled = ? ");
        args.add(false);

        return jdbcTemplate.query(buffer.toString(), args.toArray(), new BeanPropertyRowMapper<SysGroup>(SysGroup.class));

    }


    @Override
    @Transactional(readOnly = true)
    public SysGroup getByGroupname(String groupname) {
        try {
            SysGroup sysGroup = new SysGroup();
            sysGroup.setGroupname(groupname);
            return namedParameterJdbcTemplate.queryForObject(SQLUtil.generateGetSql(tableName, "groupname", false), new BeanPropertySqlParameterSource(sysGroup), new BeanPropertyRowMapper<SysGroup>(SysGroup.class));
        } catch (DataAccessException e) {
            // groupname not exists
            return null;
        }
    }


    @Override
    @Transactional(readOnly = true)
    public SysGroup getById(int id) {
        SysGroup sysGroup = new SysGroup(id);
        return namedParameterJdbcTemplate.queryForObject(SQLUtil.generateGetSql(tableName, generatedKeyName), new BeanPropertySqlParameterSource(sysGroup), new BeanPropertyRowMapper<SysGroup>(SysGroup.class));
    }
}
