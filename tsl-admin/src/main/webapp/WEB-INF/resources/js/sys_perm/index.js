/**
 * Created by chensheng on 17/5/28.
 */

jQuery(function () {

    var dt = jQuery("#sys_perm_index_tree");
    if (dt.length == 1){
        dt.tree({
            width : 200,
            url: "/sys_perm/tree.json",
            loadFilter: function(data){
                var tree = [];
                tree.push(data.data);
                return tree;
            },
            onClick : function (node) {
                var param = {id:node.id};
                dg.datagrid({url : "/sys_perm/query.json",queryParams:param});
            }
        });
    }

    var dg = jQuery("#sys_perm_index_datagrid");
    if (dg.length == 1){
        dg.datagrid({
            url : "",
            pagination : true,
            cls : "datagrid-float",
            width : 420,
            // singleSelect : true,
            sortName : "id",
            orderName : "asc",
            toolbar : [{
                iconCls: "icon-add",
                text: "Add",
                handler: function(){
                    tabRefresh("/sys_perm/create");
                }
            },"-",{
                iconCls: "icon-edit",
                text: "Edit",
                handler: function(){
                    var rows = dg.datagrid("getSelections");
                    if(rows.length == 1){
                        tabRefresh("/sys_perm/edit?id="+rows[0].id);
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
                                var form = jQuery("#sys_perm_remove_form");
                                var url = form.attr("action");
                                var ids = [];
                                jQuery.each(rows,function (i,e) {
                                    ids.push(e.id);
                                });
                                jQuery("#ids",form).val(ids.join());
                                form.form('submit',{
                                    url: url,
                                    success: function (data) {
                                        data = jQuery.parseJSON(data);
                                        if(data.success){
                                            dg.datagrid('reload');
                                        }else{
                                            jQuery.messager.show({msg: data.message});
                                        }
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
                {field: "id",checkbox:true}
                ,{field: "permcode",title: "权限代码"}
                ,{field: "viewname",title: "显示名称"}
                ,{field: "url",title: "链接"}
            ]]
        });
    }
});