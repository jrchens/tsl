<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>SysMenu list</title>
    <meta charset="UTF-8">
    <%@ include file="../common/css.jsp" %>
    <%@ include file="../common/js.jsp" %>

</head>
<body>
<div class="easyui-panel" title="System Menu Manager"
     style="width:240px;padding:10px;"
     data-options="closable:false,collapsible:false,minimizable:false,maximizable:false,cls:'clear-fix',
        tools:[{
          iconCls:'icon-reload',
          handler : function(){
            var obj = jQuery('#sys_menu_index_tree');
            var root = obj.tree('getRoot');
            obj.tree('reload');
          }
        }]
     ">
    <div id="sys_menu_index_tree" title="SysMenu Tree"></div>
</div>

<div id="sys_menu_index_datagrid" title="SysMenu List"></div>

<form:form id="sys_menu_remove_form" method="post" servletRelativeAction="/sys_menu/remove.json" commandName="sysMenu">
    <form:hidden path="ids"/>
    <form:errors path="ids"/>
</form:form>

<script type="text/javascript" src="/resources/js/sys_menu/index.js"></script>

</body>

</html>
