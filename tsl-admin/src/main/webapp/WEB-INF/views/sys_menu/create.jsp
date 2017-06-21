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

<div class="easyui-panel" title="New SysMenu" style="width:100%;max-width:400px;padding:30px 60px;">

    <form:form id="sys_menu_create_form" method="post" servletRelativeAction="/sys_menu/save.json" commandName="sysMenu">
        <div style="margin-bottom:20px">
            <form:select path="pid" cssClass="easyui-combobox"   data-options="label:'Parent Menu:',required:true" cssStyle="width: 100%;">
                <form:option value="${treeNode.id}">${treeNode.text}</form:option>
                <form:options itemLabel="text" itemValue="id" items="${treeNodeList}"></form:options>
            </form:select>
            <form:errors path="pid" />
        </div>
        <div style="margin-bottom:20px">
            <form:input path="viewname" cssClass="easyui-textbox"  data-options="label:'Viewname:',required:true,validType:{length:[2,32]}" cssStyle="width: 100%;"/>
            <form:errors path="viewname" />
        </div>
        <div style="margin-bottom:20px">
            <form:input path="url" cssClass="easyui-textbox"  data-options="label:'URL:',required:true,validType:{length:[0,200]}" cssStyle="width: 100%;"/>
            <form:errors path="url" />
        </div>
        <div style="margin-bottom:20px">
            <form:input path="srt" cssClass="easyui-numberspinner"  data-options="label:'srt:',required:true,min:10,max:1000,increment:10" cssStyle="width: 100%;"/>
            <form:errors path="srt" />
        </div>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="sys_menu_save()" style="width:80px" data-options="iconCls:'icon-save'">Save</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="tabRefresh('/sys_menu/index')" style="width:80px" data-options="iconCls:'icon-back'">Back</a>
        </div>
    </form:form>

    <script type="text/javascript" src="/resources/js/sys_menu/create.js"></script>
</div>

</body>
</html>
