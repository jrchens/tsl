<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chensheng
  Date: 17/5/28
  Time: 下午5:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>user edit</title>
    <meta charset="UTF-8">
    <%@ include file="../common/css.jsp" %>
    <%@ include file="../common/js.jsp" %>

</head>
<body>

<div class="easyui-panel" title="Edit User" style="width:100%;max-width:400px;padding:30px 60px;" data-options="cls: 'clear-fix'">

    <form:form id="sys_user_update_form" method="post" servletRelativeAction="/sys_user/update.json" commandName="sysUser">
        <form:hidden path="id"/>
        <div style="margin-bottom:20px">
            <form:input path="username" cssClass="easyui-textbox"  data-options="label:'username:',required:true,editable:false" cssStyle="width: 100%;"/>
            <form:errors path="username" />
        </div>
        <div style="margin-bottom:20px">
            <form:input path="viewname" cssClass="easyui-textbox"  data-options="label:'viewname:',required:true" cssStyle="width: 100%;"/>
            <form:errors path="viewname" />
        </div>
        <%--<div style="margin-bottom:20px">--%>
            <%--<form:select path="rid" items="${sysRoleList}" itemLabel="viewname" itemValue="id" cssClass="easyui-combobox" data-options="label:'role:',required:true,editable:false"  cssStyle="width: 100%;"></form:select>--%>
            <%--<form:errors path="rid" />--%>
        <%--</div>--%>
        <div style="margin-bottom:20px">
            <form:password path="password" cssClass="easyui-tooltip easyui-passwordbox"  data-options="label:'password:'" cssStyle="width: 100%;"/>
            <form:errors path="password" />
        </div>
        <div style="margin-bottom:20px">
        <form:select path="gid" items="${sysGroupList}" itemLabel="viewname" itemValue="id" cssClass="easyui-combobox" data-options="label:'Group:',required:true,editable:false"  cssStyle="width: 100%;"></form:select>
        <form:errors path="gid" />
        </div>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="sys_user_update()" style="width:80px" data-options="iconCls:'icon-save'">Update</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="tabRefresh('/sys_user/index')" style="width:80px" data-options="iconCls:'icon-back'">Back</a>
        </div>
    </form:form>

    <script type="text/javascript" src="/resources/js/sys_user/edit.js"></script>

</div>

<table class="easyui-datagrid"
       data-options="title: 'User Group',width:400,cls: 'clear-fix',
        url: '/sys_user_group/query?t=u&uid=${sysUser.id}&gid=${sysUser.gid}',method: 'get',
        checkOnSelect: false,
        selectOnCheck: false,
        onCheck:function(index,row){
            var dg = jQuery(this);
            var data = {uid:${sysUser.id},gid:row.id};
            jQuery.post('/sys_user_group/save.json',data,function(data,status,xhr){
                if(data.success){
                    jQuery.messager.show({msg: 'Save Success'});
                    dg.datagrid('reload');
                }
            });
        },
        onUncheck:function(index,row){
            var dg = jQuery(this);
            var data = {ids:row.ids};
            jQuery.post('/sys_user_group/remove.json',data,function(data,status,xhr){
            if(data.success){
                jQuery.messager.show({msg: 'Remove Success'});
                dg.datagrid('reload');
            }
            });
        }">
    <thead>
    <tr>
        <th data-options="field:'id',checkbox:true">ID</th>
        <th data-options="field:'groupname'">Group Name</th>
        <th data-options="field:'viewname'">View Name</th>
    </tr>
    </thead>
</table>

</body>

</html>
