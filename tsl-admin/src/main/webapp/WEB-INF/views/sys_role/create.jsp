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

<div class="easyui-panel" title="New SysRole" style="width:100%;max-width:400px;padding:30px 60px;">

    <form:form id="sys_role_create_form" method="post" servletRelativeAction="/sys_role/save.json" commandName="sysRole">
        <div style="margin-bottom:20px">
            <form:input path="rolename" cssClass="easyui-textbox"  data-options="label:'rolename:',required:true" cssStyle="width: 100%;"/>
            <form:errors path="rolename" />
        </div>
        <div style="margin-bottom:20px">
            <form:input path="viewname" cssClass="easyui-textbox"  data-options="label:'viewname:',required:true" cssStyle="width: 100%;"/>
            <form:errors path="viewname" />
        </div>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="sys_role_save()" style="width:80px" data-options="iconCls:'icon-save'">Save</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="tabRefresh('/sys_role/index')" style="width:80px" data-options="iconCls:'icon-back'">Back</a>
        </div>
    </form:form>

    <script type="text/javascript" src="/resources/js/sys_role/create.js"></script>
</div>

</body>
</html>
