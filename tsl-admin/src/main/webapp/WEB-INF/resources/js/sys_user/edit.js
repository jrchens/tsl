/**
 * Created by chensheng on 17/5/28.
 */
function sys_user_update() {
    var form = jQuery('#sys_user_update_form');
    var url = form.attr("action");
    form.form('submit',{
        url: url,
        // onSubmit: function(param){
        //     return true;
        // },
        success: function (data) {
            data = jQuery.parseJSON(data);
            if(data.success){
                tabRefresh("/sys_user/index");
            }else{
                jQuery.messager.show({msg: data.message});
            }
        }
    });
}
