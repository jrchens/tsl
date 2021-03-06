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
    <title>user create</title>
    <meta charset="UTF-8">
    <%@ include file="../common/css.jsp" %>
    <%@ include file="../common/js.jsp" %>

</head>
<body>

<div class="easyui-panel" title="New User" style="width:100%;max-width:400px;padding:30px 60px;">

    <form:form id="sys_user_create_form" method="post" servletRelativeAction="/sys_user/save.json" commandName="sysUser">
        <div style="margin-bottom:20px">
            <form:input path="username" cssClass="easyui-textbox"  data-options="label:'username:',required:true,
            validType:{
                length:[4,32],
                remote:['/valid.json?code=100','username']
                }" cssStyle="width: 100%;"/>
            <form:errors path="username" />
        </div>
        <div style="margin-bottom:20px">
            <form:input path="viewname" cssClass="easyui-textbox"  data-options="label:'viewname:',required:true,validType:{length:[2,32]}" cssStyle="width: 100%;"/>
            <form:errors path="viewname" />
        </div>
        <div style="margin-bottom:20px">
            <form:password path="password" cssClass="easyui-passwordbox"  data-options="label:'password:',required:true,validType:{length:[4,32]}" cssStyle="width: 100%;"/>
            <form:errors path="password" />
        </div>
        <%--<div style="margin-bottom:20px">--%>
            <%--<form:select path="gid" items="${sysGroupList}" itemLabel="viewname" itemValue="id" cssClass="easyui-combobox" data-options="label:'Group:',required:true,editable:false"  cssStyle="width: 100%;"></form:select>--%>
            <%--<form:errors path="gid" />--%>
        <%--</div>--%>
        <%--<div style="margin-bottom:20px">--%>
            <%--<form:select path="rid" items="${sysRoleList}" itemLabel="viewname" itemValue="id" cssClass="easyui-combobox" data-options="label:'Role:',required:true,editable:false"  cssStyle="width: 100%;"></form:select>--%>
            <%--<form:errors path="rid" />--%>
        <%--</div>--%>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="sys_user_save()" style="width:80px" data-options="iconCls:'icon-save'">Save</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="tabRefresh('/sys_user/index')" style="width:80px" data-options="iconCls:'icon-back'">Back</a>
        </div>
    </form:form>

    <script type="text/javascript" src="/resources/js/sys_user/create.js"></script>
</div>

</body>
</html>
