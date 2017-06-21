<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<div class="easyui-layout" data-options="fit:true">


    <div data-options="region:'north'" style="height:32px">
        <div style="float: right; display: inline; height: 22px; line-height: 22px; padding: 4px">
            <div style="float: left; display: inline; margin-right: 12px;">${currentUser.viewname}</div>
            <div style="float: left; display: inline; margin-right: 12px;"><a href="/logout">Exit</a> </div>
        </div>
    </div>


    <%--<div data-options="region:'south',split:true" style="height:50px;">copy right</div>--%>

    <%--
    <div data-options="region:'east',split:true" title="East" style="width:180px;">
        <ul class="easyui-tree" data-options="url:'tree_data1.json',method:'get',animate:true,dnd:true"></ul>
    </div>
    --%>

    <div data-options="region:'west',split:true" title="West" style="width:180px;">
        <%--
        // USE TREE MENU
        <div class="easyui-accordion" data-options="fit:true,border:false">
            <div title="Title1" style="padding:10px;">
                content1
            </div>
            <div title="Title2" data-options="selected:true" style="padding:10px;">
                content2
            </div>
            <div title="Title3" style="padding:10px">
                content3
            </div>
        </div>--%>

        <div class="easyui-accordion" data-options="fit:true,border:false">
            <c:forEach var="menuGroup" items="${sysMenuGroupList}">
            <div title="${menuGroup.viewname}" style="padding:10px;">
                <c:forEach var="menu" items="${menuGroup.children}">
                <div class="menu-link"><a id="${menu.id}" href="javascript:;">${menu.viewname}</a></div>
                </c:forEach>
            </div>
            </c:forEach>
        </div>

    </div>

    <div data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">
        <div id="1C257E98E2FC405A8347FB1B3AE0F3CD" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
            <%--
            <div title="DataGrid" style="padding:5px">
                <table class="easyui-datagrid"
                       data-options="url:'datagrid_data1.json',method:'get',singleSelect:true,fit:true,fitColumns:true">
                    <thead>
                    <tr>
                        <th data-options="field:'itemid'" width="80">Item ID</th>
                        <th data-options="field:'productid'" width="100">Product ID</th>
                        <th data-options="field:'listprice',align:'right'" width="80">List Price</th>
                        <th data-options="field:'unitcost',align:'right'" width="80">Unit Cost</th>
                        <th data-options="field:'attr1'" width="150">Attribute</th>
                        <th data-options="field:'status',align:'center'" width="50">Status</th>
                    </tr>
                    </thead>
                </table>
            </div>--%>
        </div>
    </div>
</div>
<script type="text/javascript">
    var LAYOUT_TABS_CENTER = jQuery('#1C257E98E2FC405A8347FB1B3AE0F3CD');
</script>

<script type="text/javascript" src="/resources/js/index.js"></script>

</body>

</html>
