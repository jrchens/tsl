/**
 * Created by chensheng on 17/5/28.
 */

jQuery(function () {
    var dg = jQuery("#sys_role_index_datagrid");
    if (dg.length == 1){
        dg.datagrid({
            url : "/sys_role/query",
            columns : [[
                {field: "rolename",title: "角色名"},
                {field: "viewname",title: "显示名"}
            ]]
        });
    }
});