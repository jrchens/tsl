package db;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.poi.util.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by chensheng on 17/5/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-test.xml"})
public class GenerateCode {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void init() {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        // Assert.notNull(jdbcTemplate);
    }

    @Test
    public void testConnection() {
        Timestamp now = jdbcTemplate.queryForObject("select now()", Timestamp.class);
        Assert.notNull(now);
    }

    @Test
    public void testGenerateCode() {
        try {
            // show columns from sys_user;
            // DESCRIBE sys_user
            List<Map<String,String>> fields = Lists.newArrayList();
            String tablename = "sys_role";
            String clazzName = "sysRole";
            String ClazzName = StringUtils.capitalize(clazzName);



            List<Map<String, Object>> columns = jdbcTemplate.queryForList("show columns from " + tablename);
            for (Map<String, Object> field :
                    columns) {
                String fieldName = ObjectUtils.getDisplayString(field.get("Field"));
                String fieldType = ObjectUtils.getDisplayString(field.get("Type"));
                if ("id".equalsIgnoreCase(fieldName) || "disabled".equalsIgnoreCase(fieldName)
                        || "deleted".equalsIgnoreCase(fieldName) || "cruser".equalsIgnoreCase(fieldName)
                        || "crtime".equalsIgnoreCase(fieldName) || "mduser".equalsIgnoreCase(fieldName)
                        || "mdtime".equalsIgnoreCase(fieldName)
                        ) {
                    continue;
                }

                Map<String, String> column = Maps.newHashMap();
                String type = "String";
                // int,tinyint,varchar,datetime,date
                if (fieldType.startsWith("int")) {
                    type = "int";
                }else if (fieldType.startsWith("tinyint")) {
                    type = "boolean";
                }else if (fieldType.startsWith("datetime")) {
                    type = "Timestamp";
                }else if (fieldType.startsWith("date")) {
                    type = "Date"; // java.sql.Date
                }else if (fieldType.startsWith("time")) {
                    type = "Time"; // java.sql.Time
                }
                String name = fieldName;
                String Name = StringUtils.capitalize(fieldName);
                column.put("type", type);
                column.put("name", name);
                column.put("Name", Name);

                fields.add(column);

            }

            File dir = ResourceUtils.getFile("classpath:vm");

            Properties prop = new Properties();
            prop.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, dir.getAbsolutePath());
            Velocity.init(prop);

            VelocityContext context = new VelocityContext();

            context.put("tablename", tablename);
            context.put("clazzName", clazzName);
            context.put("ClazzName", ClazzName);
            context.put("fields", fields);

            Template pojoTemplate = Velocity.getTemplate("pojo.vm");
            Writer pojoWriter = new FileWriter(new File("/Users/chensheng/IdeaProjects/tsl/tsl-common/src/main/java/me/simple/domain/"+ClazzName+".java"));
            pojoTemplate.merge(context, pojoWriter);
            IOUtils.closeQuietly(pojoWriter);

            Template serviceTemplate = Velocity.getTemplate("service.vm");
            Writer serviceWriter = new FileWriter(new File("/Users/chensheng/IdeaProjects/tsl/tsl-common/src/main/java/me/simple/sys/service/"+ClazzName+"Service.java"));
            serviceTemplate.merge(context, serviceWriter);
            IOUtils.closeQuietly(serviceWriter);

            Template serviceImplTemplate = Velocity.getTemplate("service.impl.vm");
            Writer serviceImplWriter = new FileWriter(new File("/Users/chensheng/IdeaProjects/tsl/tsl-common/src/main/java/me/simple/sys/service/impl/"+ClazzName+"ServiceImpl.java"));
            serviceImplTemplate.merge(context, serviceImplWriter);
            IOUtils.closeQuietly(serviceImplWriter);

            Template controllerTemplate = Velocity.getTemplate("controller.vm");
            Writer controllerWriter = new FileWriter(new File("/Users/chensheng/IdeaProjects/tsl/tsl-admin/src/main/java/me/simple/sys/controller/"+ClazzName+"Controller.java"));
            controllerTemplate.merge(context, controllerWriter);
            IOUtils.closeQuietly(controllerWriter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
