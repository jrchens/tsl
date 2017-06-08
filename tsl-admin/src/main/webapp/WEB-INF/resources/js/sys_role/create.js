function sys_role_save() {
    var form = jQuery('#sys_role_create_form');
    var url = form.attr("action");
    form.form('submit',{
        url: url,
        success: function (data) {
            data = jQuery.parseJSON(data);
            if(data.success){
                tabRefresh("/sys_role/index");
            }else{
                var errors = data.data;
                var s = [];
                for (var k in errors){
                    s.push(k+" : "+errors[k]);
                }
                jQuery.messager.show({msg: s.join("<br>")});
            }
        }
    });
}