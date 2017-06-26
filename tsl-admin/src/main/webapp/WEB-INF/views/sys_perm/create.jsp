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

<div class="easyui-panel" title="New SysPerm" style="width:100%;max-width:400px;padding:30px 60px;">

    <form:form id="sys_perm_create_form" method="post" servletRelativeAction="/sys_perm/save.json" commandName="sysPerm">
        <div style="margin-bottom:20px">
            <form:select path="pid" cssClass="easyui-combotree"
                         data-options="label:'Parent Perm:',required:true,editable:false,
                            url: '/sys_perm/tree.json',
                            loadFilter: function(data){
                                var tree = [];
                                tree.push(data.data);
                                return tree;
                            }" cssStyle="width: 100%;">
            </form:select>
            <form:errors path="pid" />
        </div>
        <div style="margin-bottom:20px">
            <form:input path="permcode" cssClass="easyui-textbox"  data-options="label:'PermCode:',required:true,validType:{length:[2,100]}" cssStyle="width: 100%;"/>
            <form:errors path="permcode" />
        </div>
        <div style="margin-bottom:20px">
            <form:input path="viewname" cssClass="easyui-textbox"  data-options="label:'Viewname:',required:true,validType:{length:[2,32]}" cssStyle="width: 100%;"/>
            <form:errors path="viewname" />
        </div>
        <div style="margin-bottom:20px">
            <form:input path="url" cssClass="easyui-textbox"  data-options="label:'URL:',required:false,validType:{length:[0,200]}" cssStyle="width: 100%;"/>
            <form:errors path="url" />
        </div>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="sys_perm_save()" style="width:80px" data-options="iconCls:'icon-save'">Save</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="tabRefresh('/sys_perm/index')" style="width:80px" data-options="iconCls:'icon-back'">Back</a>
        </div>
    </form:form>

    <script type="text/javascript" src="/resources/js/sys_perm/create.js"></script>
</div>

</body>
</html>
