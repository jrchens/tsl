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
    <title>group edit</title>
    <meta charset="UTF-8">
    <%@ include file="../common/css.jsp" %>
    <%@ include file="../common/js.jsp" %>

</head>
<body>

<div class="easyui-panel" title="Edit Group" style="width:100%;max-width:400px;padding:30px 60px;">

    <form:form id="sys_group_update_form" method="post" servletRelativeAction="/sys_group/update.json" commandName="sysGroup">
        <form:hidden path="id"/>
        <div style="margin-bottom:20px">
            <form:input path="groupname" cssClass="easyui-textbox"  data-options="label:'groupname:',required:true,editable:false" cssStyle="width: 100%;"/>
            <form:errors path="groupname" />
        </div>
        <div style="margin-bottom:20px">
            <form:input path="viewname" cssClass="easyui-textbox"  data-options="label:'viewname:',required:true,validType:{length:[2,32]}" cssStyle="width: 100%;"/>
            <form:errors path="viewname" />
        </div>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="sys_group_update()" style="width:80px" data-options="iconCls:'icon-save'">Update</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="tabRefresh('/sys_group/index')" style="width:80px" data-options="iconCls:'icon-back'">Back</a>
        </div>
    </form:form>

    <script type="text/javascript" src="/resources/js/sys_group/edit.js"></script>
</div>

</body>

</html>
