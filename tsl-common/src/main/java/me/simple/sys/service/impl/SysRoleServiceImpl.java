package me.simple.sys.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import me.simple.domain.CurrentUser;
import me.simple.domain.Pageable;
import me.simple.domain.SysRole;
import me.simple.sys.service.SysRoleService;
import me.simple.util.SQLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SysRoleServiceImpl implements SysRoleService {

    private static final Logger logger = LoggerFactory.getLogger(SysRoleService.class);
    public static final String tableName = "sys_role";
    private static final String generatedKeyName = "id";
    private static final List<String> insertColumns = Lists.newArrayList("rolename","viewname","disabled","deleted","cruser","crtime");
    private static final List<String> updateColumns = Lists.newArrayList("viewname","mduser","mdtime");

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
    public int save(SysRole sysRole , CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysRole.setCruser(user);
        sysRole.setCrtime(time);
        return simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(sysRole)).intValue();
    }

    @Override
    public int remove(SysRole sysRole , CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysRole.setMduser(user);
        sysRole.setMdtime(time);

        int aff = 0;
        String ids = sysRole.getIds();
        List<String> idlist = Splitter.on(",").splitToList(ids);
            for (String id :idlist) {
            sysRole.setId(Integer.parseInt(id));
            aff += namedParameterJdbcTemplate.update(SQLUtil.generateRemoveSql(tableName,generatedKeyName),new BeanPropertySqlParameterSource(sysRole));
        }

        return aff;
    }

    @Override
    public int update(SysRole sysRole , CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysRole.setMduser(user);
        sysRole.setMdtime(time);
        return namedParameterJdbcTemplate.update(SQLUtil.generateUpdateSql(tableName,updateColumns,generatedKeyName),new BeanPropertySqlParameterSource(sysRole));
    }

    @Override
    @Transactional(readOnly = true)
    public SysRole get(SysRole sysRole , CurrentUser currentUser) {
        return namedParameterJdbcTemplate.queryForObject(SQLUtil.generateGetSql(tableName,generatedKeyName),new BeanPropertySqlParameterSource(sysRole),new BeanPropertyRowMapper<SysRole>(SysRole.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysRole> query(SysRole sysRole , Pageable pageable, CurrentUser currentUser) {
        List<Object> args = Lists.newArrayList();
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM ").append(tableName).append(" ");
        buffer.append("WHERE deleted = 0 ");

        int total = jdbcTemplate.queryForObject(SQLUtil.generateCountSQL(buffer.toString()),args.toArray(),Integer.class);
        pageable.setTotal(total);

        SQLUtil.sortingAndPaging(buffer,pageable);

        return jdbcTemplate.query(buffer.toString(),args.toArray(),new BeanPropertyRowMapper<SysRole>(SysRole.class));
    }
}
