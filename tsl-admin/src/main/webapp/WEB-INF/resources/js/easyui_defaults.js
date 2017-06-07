/**
 * Created by chensheng on 17/5/28.
 */
jQuery.fn.datagrid.defaults.method = "GET";
/*
jQuery.extend(jQuery.fn.validatebox.defaults.rules, {
    unique: {
        validator: function(value, param){
            var valid = false;
            var data = {};
            data[param[1]] = value;
            jQuery.ajax({
                url: param[0],
                data: data,
                type: "get",
                dataType: "json",
                async: false,
                cache:false,
                success: function(data,status,xhr){
                    valid = data;
                }
            });
            return valid;
        },
        message: 'value exists'
    }
});
*/