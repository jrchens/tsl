<%--
  Created by IntelliJ IDEA.
  User: chensheng
  Date: 17/5/28
  Time: 下午5:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>role edit</title>
    <meta charset="UTF-8">

    <link rel="stylesheet" type="text/css" href="http://assets.local.com/jquery-easyui-1.5.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://assets.local.com/jquery-easyui-1.5.2/themes/icon.css">

    <script type="text/javascript" src="http://assets.local.com/jquery-easyui-1.5.2/jquery.min.js"></script>
    <script type="text/javascript" src="http://assets.local.com/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="http://assets.local.com/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>

<div class="easyui-panel" title="Edit Role" style="width:100%;max-width:400px;padding:30px 60px;">
    <form:form id="sys_role_edit_form" commandName="sysRole" servletRelativeAction="/sys_role/update" method="POST">
        <form:hidden path="id"/>
        <div style="margin-bottom:20px">
            <form:input path="rolename" cssClass="easyui-textbox" cssStyle="width:100%" data-options="label:'rolename:',required:true"/>
        </div>
        <div style="margin-bottom:20px">
            <form:input path="viewname" cssClass="easyui-textbox" cssStyle="width:100%" data-options="label:'viewname:',required:true"/>
        </div>
    </form:form>
    <form:form id="sys_role_remove_form" commandName="sysRole" servletRelativeAction="/sys_role/remove" method="POST" cssStyle="display: none">
        <form:hidden path="id"/>
    </form:form>
    <div style="text-align:center;padding:5px 0">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="sys_role_update()" style="width:80px">Update</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="sys_role_remove()" style="width:80px">Remove</a>
    </div>
</div>

</body>
<script type="text/javascript" src="/resources/js/easyui_defaults.js"></script>
<script type="text/javascript" src="/resources/js/sys_role/edit.js"></script>
</html>
