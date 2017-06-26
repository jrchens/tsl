/**
 * Created by chensheng on 17/5/28.
 */
function sys_perm_update() {
    var form = jQuery('#sys_perm_update_form');
    var url = form.attr("action");
    form.form('submit',{
        url: url,
        success: function (data) {
            data = jQuery.parseJSON(data);
            if(data.success){
                tabRefresh("/sys_perm/index");
            }else{
                jQuery.messager.show({msg: data.message});
            }
        }
    });
}
