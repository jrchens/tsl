package me.simple.sys.service.impl;

import com.google.common.collect.Lists;
import me.simple.domain.CurrentUser;
import me.simple.domain.Pageable;
import me.simple.domain.SysMenu;
import me.simple.sys.service.SysMenuService;
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
public class SysMenuServiceImpl implements SysMenuService {

    private static final Logger logger = LoggerFactory.getLogger(SysMenuService.class);
    public static final String tableName = "sys_menu";
    private static final String generatedKeyName = "id";
    private static final List<String> insertColumns = Lists.newArrayList("cruser","crtime");
    private static final List<String> updateColumns = Lists.newArrayList("mduser","mdtime");

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
    public int save(SysMenu sysMenu , CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysMenu.setCruser(user);
        sysMenu.setCrtime(time);
        return simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(sysMenu)).intValue();
    }

    @Override
    public int remove(SysMenu sysMenu , CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysMenu.setMduser(user);
        sysMenu.setMdtime(time);
        return namedParameterJdbcTemplate.update(SQLUtil.generateRemoveSql(tableName,generatedKeyName),new BeanPropertySqlParameterSource(sysMenu));
    }

    @Override
    public int update(SysMenu sysMenu , CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysMenu.setMduser(user);
        sysMenu.setMdtime(time);
        return namedParameterJdbcTemplate.update(SQLUtil.generateUpdateSql(tableName,updateColumns,generatedKeyName),new BeanPropertySqlParameterSource(sysMenu));
    }

    @Override
    @Transactional(readOnly = true)
    public SysMenu get(SysMenu sysMenu , CurrentUser currentUser) {
        return namedParameterJdbcTemplate.queryForObject(SQLUtil.generateGetSql(tableName,generatedKeyName),new BeanPropertySqlParameterSource(sysMenu),new BeanPropertyRowMapper<SysMenu>(SysMenu.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysMenu> query(SysMenu sysMenu , Pageable pageable, CurrentUser currentUser) {
        List<Object> args = Lists.newArrayList();
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM ").append(tableName).append(" ");
        buffer.append("WHERE deleted = 0 ");

        int total = jdbcTemplate.queryForObject(SQLUtil.generateCountSQL(buffer.toString()),args.toArray(),Integer.class);
        pageable.setTotal(total);

        SQLUtil.sortingAndPaging(buffer,pageable);

        return jdbcTemplate.query(buffer.toString(),args.toArray(),new BeanPropertyRowMapper<SysMenu>(SysMenu.class));
    }
}
