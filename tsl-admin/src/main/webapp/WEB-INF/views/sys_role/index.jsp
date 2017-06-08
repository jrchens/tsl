<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>SysRole list</title>
    <meta charset="UTF-8">
    <%@ include file="../common/css.jsp" %>
    <%@ include file="../common/js.jsp" %>

</head>
<body>
<div id="sys_role_index_datagrid" title="SysRole List"></div>

<form:form id="sys_role_remove_form" method="post" servletRelativeAction="/sys_role/remove.json" commandName="sysRole">
    <form:hidden path="ids"/>
    <form:errors path="ids"/>
</form:form>

<script type="text/javascript" src="/resources/js/sys_role/index.js"></script>

</body>

</html>
