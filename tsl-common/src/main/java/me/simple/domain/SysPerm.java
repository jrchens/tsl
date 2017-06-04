package me.simple.domain;

public class SysPerm extends Base {

private int pid;
private String permcode;
private String viewname;
private String url;

public int getPid(){
  return pid;
}
public void setPid(int pid){
  this.pid = pid;
}
public String getPermcode(){
  return permcode;
}
public void setPermcode(String permcode){
  this.permcode = permcode;
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