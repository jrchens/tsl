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

<form:form id="sys_user_remove_form" method="post" servletRelativeAction="/sys_user/remove.json" commandName="sysUser">
    <form:hidden path="ids"/>
    <form:errors path="ids"/>
</form:form>

<script type="text/javascript" src="/resources/js/sys_user/index.js"></script>

</body>

</html>
