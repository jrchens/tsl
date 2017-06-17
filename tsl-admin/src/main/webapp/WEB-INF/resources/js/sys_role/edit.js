function sys_role_update() {
    var form = jQuery('#sys_role_update_form');
    var url = form.attr("action");
    form.form('submit',{
        url: url,
        success: function (data) {
            data = jQuery.parseJSON(data);
            if(data.success){
                tabRefresh("/sys_role/index");
            }else{
                jQuery.messager.show({msg: data.message});
            }
        }
    });
}
