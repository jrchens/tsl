/**
 * Created by chensheng on 17/5/28.
 */
function sys_menu_save() {
    var form = jQuery('#sys_menu_create_form');
    var url = form.attr("action");
    form.form('submit',{
        url: url,
        success: function (data) {
            data = jQuery.parseJSON(data);
            if(data.success){
                tabRefresh("/sys_menu/index");
            }else{
                jQuery.messager.show({msg: data.message});
            }
        }
    });
}