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

            String generateBaseDir = "/Users/chensheng/IdeaProjects/generate";

//            String generateBaseDir = "D:/CS/svn/jrcredit/trunk/hdty";
//            String packageName = "/common/src/main/java/com/hdty/backend";
            String tablename = "sys_role";
            String clazzName = "sysRole";
            String modepath = "sys_role";
            String vmprefix = "";
            String ClazzName = StringUtils.capitalize(clazzName);
            


            List<Map<String, Object>> columns = jdbcTemplate.queryForList("show columns from " + tablename);
            for (Map<String, Object> field :
                    columns) {
                String fieldName = ObjectUtils.getDisplayString(field.get("Field"));
                String fieldType = ObjectUtils.getDisplayString(field.get("Type"));
                /*if ("id".equalsIgnoreCase(fieldName) || "disabled".equalsIgnoreCase(fieldName)
                        || "deleted".equalsIgnoreCase(fieldName) || "cruser".equalsIgnoreCase(fieldName)
                        || "crtime".equalsIgnoreCase(fieldName) || "mduser".equalsIgnoreCase(fieldName)
                        || "mdtime".equalsIgnoreCase(fieldName)
                        ) {
                    continue;
                }*/

                Map<String, String> column = Maps.newHashMap();
                String type = "String";
                // int,tinyint,varchar,datetime,date
                if (fieldType.startsWith("int")) {
                    type = "int";
                }else if (fieldType.startsWith("bigint")) {
                    type = "long";
                }else if (fieldType.startsWith("double")) {
                    type = "double";
                }else if (fieldType.startsWith("tinyint")) {
                    type = "boolean";
                }else if (fieldType.startsWith("datetime")) {
                    type = "java.sql.Timestamp";
                }else if (fieldType.startsWith("date")) {
                    type = "java.sql.Date"; // java.sql.Date
                }else if (fieldType.startsWith("time")) {
                    type = "java.sql.Time"; // java.sql.Time
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

            context.put("modepath", modepath);
            context.put("tablename", tablename);
            context.put("clazzName", clazzName);
            context.put("ClazzName", ClazzName);
            context.put("fields", fields);

            Template pojoTemplate = Velocity.getTemplate(vmprefix+"pojo.vm");
            Writer pojoWriter = new FileWriter(new File(generateBaseDir + "/"+ClazzName+".java"));
            pojoTemplate.merge(context, pojoWriter);
            IOUtils.closeQuietly(pojoWriter);

            Template serviceTemplate = Velocity.getTemplate(vmprefix+"service.vm");
            Writer serviceWriter = new FileWriter(new File(generateBaseDir + "/"+ClazzName+"Service.java"));
            serviceTemplate.merge(context, serviceWriter);
            IOUtils.closeQuietly(serviceWriter);

            Template serviceImplTemplate = Velocity.getTemplate(vmprefix+"service.impl.vm");
            Writer serviceImplWriter = new FileWriter(new File(generateBaseDir + "/impl/"+ClazzName+"ServiceImpl.java"));
            serviceImplTemplate.merge(context, serviceImplWriter);
            IOUtils.closeQuietly(serviceImplWriter);

            Template controllerTemplate = Velocity.getTemplate(vmprefix+"controller.vm");
            Writer controllerWriter = new FileWriter(new File(generateBaseDir + "/"+ClazzName+"Controller.java"));
            controllerTemplate.merge(context, controllerWriter);
            IOUtils.closeQuietly(controllerWriter);


            String[] vms = {"index.js","index.jsp","create.js","create.jsp","edit.js","edit.jsp"};
            for (String vm: vms) {
                Template tmpl = Velocity.getTemplate(vmprefix+vm+".vm");
                Writer writer = new FileWriter(new File(generateBaseDir + "/" + vm));
                tmpl.merge(context, writer);
                IOUtils.closeQuietly(writer);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
