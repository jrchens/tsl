<%--
  Created by IntelliJ IDEA.
  User: chensheng
  Date: 17/5/24
  Time: 下午10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>管理员登陆</title>
    <meta charset="UTF-8">
    <%@ include file="./common/css.jsp" %>
    <%@ include file="./common/js.jsp" %>

</head>
<body>


<div class="easyui-window" title="管理员登陆"
     data-options="modal:true,closed:false,collapsible:false,minimizable:false,maximizable:false,closable:false">
    <form:form id="user_login_form" servletRelativeAction="/login" commandName="loginUser" cssClass="easyui-form" method="post"
        data-options="novalidate:true,ajax:false,iframe:false" cssStyle="padding: 10px;width: 400px; height: 100px;">
        <div style="margin-bottom:10px">
            <form:input path="username" cssClass="easyui-textbox" cssStyle="width:100%" data-options="label:'用户名:',required:true,validType:{length:[4,18]},value:'admin'"/>
            <form:errors path="username"/>
        </div>

        <div style="margin-bottom:10px">
            <form:password path="password" cssClass="easyui-passwordbox" cssStyle="width:100%"
                        data-options="label:'密&#12288;码:',required:true,validType:{length:[4,18]},value:'123456'"/>
            <form:errors path="password"/>
        </div>

        <div style="margin-bottom:10px">
            <a href="javascript:;" class="easyui-linkbutton" onclick="userLogin()" data-options="iconCls:'icon-man'"
               style="float:right">登&#12288;陆</a>
        </div>
    </form:form>
</div>

</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/login.js"></script>

</html>
