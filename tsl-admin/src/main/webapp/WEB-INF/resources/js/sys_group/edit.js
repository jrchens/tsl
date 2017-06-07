/**
 * Created by chensheng on 17/5/28.
 */
function sys_group_update() {
    var form = jQuery('#sys_group_update_form');
    var url = form.attr("action");
    form.form('submit',{
        url: url,
        success: function () {
            tabRefresh("/sys_group/index");
        }
    });
}
