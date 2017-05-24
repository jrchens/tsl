/**
 * Created by chensheng on 17/5/24.
 */

function userLogin() {
    jQuery('#user-login-form').form('submit', {
        onSubmit: function () {
            return jQuery(this).form('enableValidation').form('validate');
        }
    });
}