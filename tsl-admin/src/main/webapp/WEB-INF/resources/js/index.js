jQuery(function(){
    jQuery(".menu-link > a").click(function(){
        var id = jQuery(this).attr("id");
        var tabsObj = jQuery('#1C257E98E2FC405A8347FB1B3AE0F3CD');
        var opendTabs = tabsObj.tabs('tabs');

        var opend = false;
        jQuery.each(opendTabs,function(i,e){
            var opts = e.panel('options');
            console.log(opts);
            if(id == opts.id){
                tabsObj.tabs('select',i);
                opend = true;
                return false;
            }
        });

        if(!opend){
            jQuery.getJSON("/sys_menu/get",{id:id},function(data, textStatus, jqXHR){
                tabsObj.tabs('add',{
                    id: id,
                    title: data.viewname,
                    selected: true,
                    href:data.url,
                    closable: true,
                    cls: "center-body"
                });
            });
        }
    });
});