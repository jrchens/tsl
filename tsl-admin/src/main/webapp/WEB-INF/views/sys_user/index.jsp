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
    <title>user list</title>
    <meta charset="UTF-8">
    <%@ include file="../common/css.jsp" %>
    <%@ include file="../common/js.jsp" %>

</head>
<body>
<div id="sys_user_index_datagrid" title="User List"></div>

<form:form id="sys_user_edit_form" method="get" servletRelativeAction="/sys_user/edit" commandName="sysUser">
    <form:hidden path="id"/>
    <form:errors path="id"/>
</form:form>
<form:form id="sys_user_remove_form" method="post" servletRelativeAction="/sys_user/remove" commandName="sysUser">
    <form:hidden path="id"/>
    <form:errors path="id"/>
</form:form>
</body>

<script type="text/javascript" src="/resources/js/sys_user/index.js"></script>
</html>
