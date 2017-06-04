/**
 * Created by chensheng on 17/5/28.
 */
function sys_role_update() {
    jQuery("#sys_role_edit_form").form('submit',{
        iframe : false,
        ajax : false
    });
}
function sys_role_remove() {
    jQuery("#sys_role_remove_form").form('submit',{
        iframe : false,
        ajax : false
    });
}