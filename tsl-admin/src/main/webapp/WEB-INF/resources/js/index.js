jQuery(function(){

    jQuery(".menu-link > a").click(function(){
        var id = jQuery(this).attr("id");
        var opendTabs = LAYOUT_TABS_CENTER.tabs('tabs');

        var opend = false;
        jQuery.each(opendTabs,function(i,e){
            var opts = e.panel('options');
            if(id == opts.id){
                // LAYOUT_TABS_CENTER.tabs('select',i);
                opend = true;
                return false;
            }
        });

            jQuery.getJSON("/sys_menu/get.json",jQuery.param({id:id}),function(data, textStatus, jqXHR){
                if(!opend){
                    LAYOUT_TABS_CENTER.tabs('add',{
                        id: id,
                        title: data.data.viewname,
                        selected: true,
                        href:data.data.url,
                        closable: true,
                        cache: false,
                        cls: 'center-body'
                    });
                }else{
                    tabRefresh(data.data.url);
                }
            });
    });

    LAYOUT_TABS_CENTER.tabs('add',{
        id:0,
        title: 'Dashboard',
        selected: true,
        closable: false,
        cls: 'center-body'
    });
});