<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>SysPerm list</title>
    <meta charset="UTF-8">
    <%@ include file="../common/css.jsp" %>
    <%@ include file="../common/js.jsp" %>

</head>
<body>
<div class="easyui-panel" title="Sys Perm Manager"
     style="width:240px;padding:10px;"
     data-options="closable:false,collapsible:false,minimizable:false,maximizable:false,cls:'clear-fix',
        tools:[{
          iconCls:'icon-reload',
          handler : function(){
            var obj = jQuery('#sys_perm_index_tree');
            var root = obj.tree('getRoot');
            obj.tree('reload');
          }
        }]
     ">
    <div id="sys_perm_index_tree" title="Sys Perm Tree"></div>
</div>

<div id="sys_perm_index_datagrid" title="SysPerm List"></div>

<form:form id="sys_perm_remove_form" method="post" servletRelativeAction="/sys_perm/remove.json" commandName="sysPerm">
    <form:hidden path="ids"/>
    <form:errors path="ids"/>
</form:form>

<script type="text/javascript" src="/resources/js/sys_perm/index.js"></script>

</body>

</html>
