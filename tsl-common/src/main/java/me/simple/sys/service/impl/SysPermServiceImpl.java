package me.simple.sys.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import me.simple.domain.*;
import me.simple.sys.service.SysPermService;
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

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class SysPermServiceImpl implements SysPermService {

    private static final Logger logger = LoggerFactory.getLogger(SysPermService.class);
    public static final String tableName = "sys_perm";
    private static final String generatedKeyName = "id";
    private static final List<String> insertColumns = Lists.newArrayList( "pid", "permcode", "viewname", "url", "deleted", "disabled", "cruser", "crtime");
    private static final List<String> updateColumns = Lists.newArrayList( "viewname", "url", "mduser", "mdtime");

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
    public int save(SysPerm sysPerm , CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysPerm.setCruser(user);
        sysPerm.setCrtime(time);
        return simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(sysPerm)).intValue();
    }

    @Override
    public int remove(SysPerm sysPerm , CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysPerm.setMduser(user);
        sysPerm.setMdtime(time);

        int aff = 0;
        String ids = sysPerm.getIds();
        List<String> idlist = Splitter.on(",").splitToList(ids);
            for (String id :idlist) {
            sysPerm.setId(Integer.parseInt(id));
            aff += namedParameterJdbcTemplate.update(SQLUtil.generateRemoveSql(tableName,generatedKeyName),new BeanPropertySqlParameterSource(sysPerm));
        }

        return aff;
    }

    @Override
    public int update(SysPerm sysPerm , CurrentUser currentUser) {
        String user = currentUser.getUsername();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        sysPerm.setMduser(user);
        sysPerm.setMdtime(time);
        return namedParameterJdbcTemplate.update(SQLUtil.generateUpdateSql(tableName,updateColumns,generatedKeyName),new BeanPropertySqlParameterSource(sysPerm));
    }

    @Override
    @Transactional(readOnly = true)
    public SysPerm get(SysPerm sysPerm , CurrentUser currentUser) {
        return namedParameterJdbcTemplate.queryForObject(SQLUtil.generateGetSql(tableName,generatedKeyName),new BeanPropertySqlParameterSource(sysPerm),new BeanPropertyRowMapper<SysPerm>(SysPerm.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysPerm> query(SysPerm sysPerm , Pageable pageable, CurrentUser currentUser) {
        int id = sysPerm.getId();
        List<Object> args = Lists.newArrayList();
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM ").append(tableName).append(" ");
        buffer.append("WHERE deleted = 0 ");
        if (id > 0) {
            buffer.append("AND pid = ? ");
            args.add(id);
        }

        int total = jdbcTemplate.queryForObject(SQLUtil.generateCountSQL(buffer.toString()),args.toArray(),Integer.class);
        pageable.setTotal(total);

        SQLUtil.sortingAndPaging(buffer,pageable);

        return jdbcTemplate.query(buffer.toString(),args.toArray(),new BeanPropertyRowMapper<SysPerm>(SysPerm.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysPerm> getChildrenByPid(int pid) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM ").append(tableName).append(" ");
        buffer.append("WHERE deleted = 0 AND pid = ? ORDER BY id ASC ");
        return jdbcTemplate.query(buffer.toString(), new BeanPropertyRowMapper<SysPerm>(SysPerm.class), new Object[]{pid});
    }

    @Override
    @Transactional(readOnly = true)
    public TreeNode getTree() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM ").append(tableName).append(" ");
        buffer.append("WHERE deleted = 0 AND pid = 0 ");
        SysPerm sysPerm = jdbcTemplate.queryForObject(buffer.toString(), new BeanPropertyRowMapper<SysPerm>(SysPerm.class));
        TreeNode root = new TreeNode(sysPerm.getId(), sysPerm.getViewname());
        getChildren(root);
        return root;
    }

    @Transactional(readOnly = true)
    private void getChildren(final TreeNode node) {
        List<SysPerm> sysPermList = getChildrenByPid(node.getId());
        if (sysPermList.isEmpty()) return;

        List<TreeNode> children = Lists.newArrayList();
        for (SysPerm sysPerm: sysPermList
                ) {
            children.add(new TreeNode(sysPerm.getId(),sysPerm.getViewname()));
        }
        node.setChildren(children);
        for (TreeNode treeNode: children
             ) {
            getChildren(treeNode);
        }
    }

}
