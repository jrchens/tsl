/**
 * Created by chensheng on 17/5/28.
 */
function sys_user_save() {
    var form = jQuery('#sys_user_create_form');
    var url = form.attr("action");
    form.form('submit',{
        url: url,
        success: function (data) {
            data = jQuery.parseJSON(data);
            if(data.success){
                tabRefresh("/sys_user/index");
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