/**
 * Created by chensheng on 17/5/28.
 */

jQuery(function () {
    var dt = jQuery("#sys_menu_index_tree");
    if (dt.length == 1){
        dt.tree({
            width : 200,
            url: "/sys_menu/tree.json",
            loadFilter: function(data){
                return data.data;
            },
            onClick : function (node) {
                var param = {id:node.id};
                dg.datagrid({url : "/sys_menu/query.json",queryParams:param});
            }
        });
    }

    var dg = jQuery("#sys_menu_index_datagrid");
    if (dg.length == 1){
        dg.datagrid({
            url : "",
            pagination : true,
            cls : "clear-fix",
            width : 420,
            // singleSelect : true,
            sortName : "srt",
            orderName : "asc",
            toolbar : [{
                iconCls: "icon-add",
                text: "Add",
                handler: function(){
                    tabRefresh("/sys_menu/create");
                }
            },"-",{
                iconCls: "icon-edit",
                text: "Edit",
                handler: function(){
                    var rows = dg.datagrid("getSelections");
                    if(rows.length == 1){
                        tabRefresh("/sys_menu/edit?id="+rows[0].id);
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
                                var form = jQuery("#sys_menu_remove_form");
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
                ,{field: "viewname",title: "显示名称"}
                ,{field: "url",title: "链接"}
                ,{field: "srt",title: "排序"}
            ]]
        });
    }
});