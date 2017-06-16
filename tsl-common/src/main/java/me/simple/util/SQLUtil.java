package me.simple.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import me.simple.domain.Pageable;

public class SQLUtil {
    private static final Logger logger = LoggerFactory.getLogger(SQLUtil.class);

//    public static String generateInsertSql(String tableName, List<String> insertColumns) {
//        StringBuffer buffer = new StringBuffer("INSERT INTO ");
//        buffer.append(tableName).append(" (");
//        buffer.append(Joiner.on(",").join(insertColumns));
//        buffer.append(") VALUES (:");
//        buffer.append(Joiner.on(",:").join(insertColumns));
//        buffer.append(") ");
//        return buffer.toString();
//    }

    public static String generateRemoveSql(String tableName, String primaryKey) {
        StringBuffer buffer = new StringBuffer("UPDATE ");
        buffer.append(tableName).append(" SET ");
        buffer.append("deleted=1,mduser=:mduser,mdtime=:mdtime ");
        buffer.append("WHERE deleted=0 AND ").append(primaryKey).append("=:").append(primaryKey);
        return buffer.toString();
    }

    public static String generateRemoveSql(String tableName, List<String> uniqueKeys) {
        StringBuffer buffer = new StringBuffer("UPDATE ");
        buffer.append(tableName).append(" SET ");
        buffer.append("deleted=1,mduser=:mduser,mdtime=:mdtime ");
        buffer.append("WHERE deleted=0 AND ");
        for (String key: uniqueKeys
             ) {
            buffer.append(key).append("=:").append(key).append(" AND ");
        }
        buffer.delete(buffer.length() - 4, buffer.length());
        return buffer.toString();
    }

//    public static String generateDisableToggleSql(String tableName, String primaryKey) {
//        StringBuffer buffer = new StringBuffer("UPDATE ");
//        buffer.append(tableName).append(" SET ");
//        buffer.append("disabled=:disabled,mduser=:mduser,mdtime=:mdtime ");
//        buffer.append("WHERE deleted=0 AND ").append(primaryKey).append("=:").append(primaryKey);
//        return buffer.toString();
//    }

    public static String generateUpdateSql(String tableName, List<String> updateColumns, String primaryKey) {
        StringBuffer buffer = new StringBuffer("UPDATE ");
        buffer.append(tableName).append(" SET ");
        for (String col : updateColumns) {
            buffer.append(col).append("=:").append(col).append(",");
        }
        buffer.deleteCharAt(buffer.length() - 1).append(" ");
        buffer.append("WHERE deleted=0 AND ").append(primaryKey).append("=:").append(primaryKey);
        return buffer.toString();
    }

//    public static String generateDeleteSql(String tableName, String primaryKey) {
//        StringBuffer buffer = new StringBuffer("DELETE FROM ");
//        buffer.append(tableName).append(" ");
//        buffer.append("WHERE ").append(primaryKey).append("=:").append(primaryKey);
//        return buffer.toString();
//    }

    public static String generateGetSql(String tableName, String primaryKey) {
        StringBuffer buffer = new StringBuffer("SELECT * FROM ");
        buffer.append(tableName).append(" ");
        buffer.append("WHERE deleted=0 AND ").append(primaryKey).append("=:").append(primaryKey);
        return buffer.toString();
    }

    public static String generateGetAllSql(String tableName, String primaryKey) {
        StringBuffer buffer = new StringBuffer("SELECT * FROM ");
        buffer.append(tableName).append(" WHERE ");
        buffer.append(primaryKey).append("=:").append(primaryKey);
        return buffer.toString();
    }

    public static String generateGetSql(String tableName, String primaryKey,boolean filterDeleted) {
        StringBuffer buffer = new StringBuffer("SELECT * FROM ");
        buffer.append(tableName).append(" ");
        if (filterDeleted) {
            buffer.append("WHERE deleted=0 ");
        }else {
            buffer.append("WHERE 1 = 1 ");
        }
        buffer.append(" AND ").append(primaryKey).append("=:").append(primaryKey);
        return buffer.toString();
    }

    public static String generateCountSQL(String querySql) {
        StringBuffer buffer = new StringBuffer(querySql);
        int index = querySql.toLowerCase().indexOf(" from ");
        buffer.delete(0, index);
        buffer.insert(0, "select count(1)");
        return buffer.toString();
    }

    public static String sortingAndPaging(final StringBuffer buffer, final Pageable pageable) {
        String sort = pageable.getSort();
        String order = pageable.getOrder();
        boolean pable = pageable.isPable();
        boolean sable = pageable.isSable();

        if (StringUtils.hasText(sort) && sable) {
            buffer.append("ORDER BY ").append(HtmlUtils.htmlEscape(sort)).append(" ");
            if (StringUtils.hasText(order) && ("asc".equalsIgnoreCase(order) || "desc".equalsIgnoreCase(order))) {
                buffer.append(HtmlUtils.htmlEscape(order)).append(" ");
            }
        }
        if (pable) {
            buffer.append("LIMIT ");
            buffer.append((pageable.getPage() - 1) * pageable.getRows());
            buffer.append(",");
            buffer.append(pageable.getRows());
        }
        return buffer.toString();
    }

    public static String like(String value) {
        StringBuffer fieldValue = new StringBuffer(StringUtils.trimWhitespace(value));
        fieldValue.insert(0, "%");
        fieldValue.insert(fieldValue.length(), "%");
        return fieldValue.toString();
    }

//    public static String notIn(String columname, String values, final List<Object> args) {
//        List<String> valueList = Splitter.on(",").splitToList(values);
//        int size = valueList.size();
//        int iMax = size - 1;
//        StringBuffer buffer = new StringBuffer();
//        for (int i = 0; i < size; i++) {
//            buffer.append(columname).append(" <> ? ");
//            args.add(valueList.get(i));
//            if (i < iMax) {
//                buffer.append("AND ");
//            }
//        }
//        return buffer.toString();
//    }

//    public static String in(String columname, String values, final List<Object> args) {
//        List<String> valueList = Splitter.on(",").splitToList(values);
//        int size = valueList.size();
//        int iMax = size - 1;
//        StringBuffer buffer = new StringBuffer();
//        for (int i = 0; i < size; i++) {
//            buffer.append(columname).append(" = ? ");
//            args.add(valueList.get(i));
//            if (i < iMax) {
//                buffer.append("OR ");
//            }
//        }
//        return buffer.toString();
//    }

}
