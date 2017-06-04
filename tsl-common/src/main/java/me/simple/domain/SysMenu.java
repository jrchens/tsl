package me.simple.domain;

public class SysMenu extends Base {

private int pid;
private String viewname;
private String url;

public int getPid(){
  return pid;
}
public void setPid(int pid){
  this.pid = pid;
}
public String getViewname(){
  return viewname;
}
public void setViewname(String viewname){
  this.viewname = viewname;
}
public String getUrl(){
  return url;
}
public void setUrl(String url){
  this.url = url;
}
}