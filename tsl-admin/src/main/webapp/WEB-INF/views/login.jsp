<%--
  Created by IntelliJ IDEA.
  User: chensheng
  Date: 17/5/24
  Time: 下午10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>管理员登陆</title>
    <meta charset="UTF-8">

    <link rel="stylesheet" type="text/css" href="http://assets.local.com/jquery-easyui-1.5.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://assets.local.com/jquery-easyui-1.5.2/themes/icon.css">

    <script type="text/javascript" src="http://assets.local.com/jquery-easyui-1.5.2/jquery.min.js"></script>
    <script type="text/javascript" src="http://assets.local.com/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="http://assets.local.com/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>

</head>
<body>


<div class="easyui-window" title="管理员登陆"
     data-options="modal:true,closed:false,collapsible:false,minimizable:false,maximizable:false,closable:false">
    <form id="user-login-form" action="${pageContext.request.contextPath }/login" class="easyui-form" method="post"
          data-options="novalidate:true,ajax:false,iframe:false" style="padding: 10px;width: 400px; height: 100px;">
        <div style="margin-bottom:10px">
            <input class="easyui-textbox" name="username" style="width:100%"
                   data-options="label:'用户名:',required:true,validType:{length:[4,18]}">
        </div>

        <div style="margin-bottom:10px">
            <input class="easyui-passwordbox" name="password" style="width:100%"
                   data-options="label:'密&#12288;码:',required:true,validType:{length:[4,18]}">
        </div>

        <div style="margin-bottom:10px">
            <a href="javascript:;" class="easyui-linkbutton" onclick="userLogin()" data-options="iconCls:'icon-man'"
               style="float:right">登&#12288;陆</a>
        </div>
    </form>
</div>

</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/login.js"></script>

</html>
