/**
 * Created by chensheng on 17/6/6.
 */
function tabRefresh(url) {
    LAYOUT_TABS_CENTER.tabs("getSelected").panel("refresh", url);
}