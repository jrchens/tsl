package me.simple.domain;

public class SysGroup extends Base {

private String groupname;
private String viewname;

public String getGroupname(){
  return groupname;
}
public void setGroupname(String groupname){
  this.groupname = groupname;
}
public String getViewname(){
  return viewname;
}
public void setViewname(String viewname){
  this.viewname = viewname;
}
}