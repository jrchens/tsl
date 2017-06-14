/**
 * Created by chensheng on 17/5/28.
 */

jQuery(function () {
    var dg = jQuery("#sys_group_index_datagrid");
    if (dg.length == 1){
        dg.datagrid({
            url : "/sys_group/query",
            pagination : true,
            cls : "clear-fix",
            width : 420,
            // singleSelect : true,
            toolbar : [{
                iconCls: "icon-add",
                text: "Add",
                handler: function(){
                    tabRefresh("/sys_group/create");
                }
            },"-",{
                iconCls: "icon-edit",
                text: "Edit",
                handler: function(){
                    var rows = dg.datagrid("getSelections");
                    if(rows.length == 1){
                        tabRefresh("/sys_group/edit?id="+rows[0].id);
                    } else {
                        jQuery.messager.show({msg: "Please Select One Record!"});
                    }
                }
            },"-",{
                iconCls: "icon-remove",
                text: "Remove",
                handler: function(){
                    var rows = dg.datagrid("getSelections");
                    if(rows.length > 0){
                        jQuery.messager.confirm('', 'Are you sure remove selected records?', function(r){
                            if (r){

                                var form = jQuery("#sys_group_remove_form");
                                var url = form.attr("action");
                                var ids = [];
                                jQuery.each(rows,function (i,e) {
                                    ids.push(e.id);
                                });
                                jQuery("#ids",form).val(ids.join());
                                form.form('submit',{
                                    url: url,
                                    success: function () {
                                        dg.datagrid('reload');
                                    }
                                });

                            }
                        });
                    }else{
                        jQuery.messager.show({msg: "Please Select One Record!"});
                    }
                }
            }],
            columns : [[
                {field: "id",checkbox:true},
                {field: "groupname",title: "Group Name"},
                {field: "viewname",title: "View Name"}
            ]]
        });
    }
});