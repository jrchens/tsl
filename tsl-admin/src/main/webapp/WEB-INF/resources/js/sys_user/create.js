/**
 * Created by chensheng on 17/5/28.
 */
function sys_user_save() {
    jQuery('#sys_user_create_form').form('submit',{
        iframe : false,
        ajax : false
    });
}