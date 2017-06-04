/**
 * Created by chensheng on 17/5/28.
 */

jQuery(function () {
    var dg = jQuery("#sys_user_index_datagrid");
    if (dg.length == 1){
        dg.datagrid({
            url : "/sys_user/query",
            toolbar : [{
                iconCls: "icon-add",
                text: "Add",
                handler: function(){
                    location.href = "/sys_user/create";
                }
            },"-",{
                iconCls: "icon-edit",
                text: "Edit",
                handler: function(){
                    var form = jQuery("#sys_user_edit_form");
                    var row = dg.datagrid('getSelected');
                    if(row){
                        jQuery("#id",form).val(row.id);
                        form.submit();
                    } else {
                        jQuery.messager.show({msg: "Please Select One Record!"});
                    }
                }
            },"-",{
                iconCls: "icon-remove",
                text: "Remove",
                handler: function(){
                    var form = jQuery("#sys_user_remove_form");
                    var row = dg.datagrid('getSelected');
                    if(row){
                        jQuery("#id",form).val(row.id);
                        form.submit();
                    } else {
                        jQuery.messager.show({msg: "Please Select One Record!"});
                    }
                }
            }],
            columns : [[
                {field: "username",title: "用户名"},
                {field: "viewname",title: "显示名"}
            ]]
        });
    }
});