/**
 * Created by chensheng on 17/5/28.
 */
jQuery.fn.datagrid.defaults.method = "GET";
jQuery.fn.combo.defaults.method = "GET";
jQuery.fn.tree.defaults.method = "GET";
jQuery.fn.combotree.defaults.method = "GET";

jQuery.extend(jQuery.fn.validatebox.defaults.rules,
    {
        remote: {
            validator: function (value, param) {
                var opts = jQuery(this).validatebox('options');
                var data = {};
                data[param[1]] = value;
                var success = false;
                var message = false;
                jQuery.ajax({
                    dataType: "json",
                    url: param[0],
                    data: data,
                    async: false,
                    cache: false,
                    type: "get",
                    success: function (data, status, xhr) {
                        success = data.success;
                        if (success == false) {
                            opts.rules['remote']['message'] = data.message;
                        }
                    }
                });
                return success;
            }, message: "Please fix this field."
        }
    }
);