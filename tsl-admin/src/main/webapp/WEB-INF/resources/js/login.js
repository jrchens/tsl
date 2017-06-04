/**
 * Created by chensheng on 17/5/24.
 */

function userLogin() {
    var loginForm = jQuery('#user-login-form');
    var url = loginForm.attr("url");
    loginForm.form('submit', {
        url: url,
        /*ajax: false,
         iframe: false,*/
        onSubmit: function () {
            return jQuery(this).form('enableValidation').form('validate');
        }
    });
}