package me.simple.domain;

public class SysRole extends Base {

private String rolename;
private String viewname;

public String getRolename(){
  return rolename;
}
public void setRolename(String rolename){
  this.rolename = rolename;
}
public String getViewname(){
  return viewname;
}
public void setViewname(String viewname){
  this.viewname = viewname;
}
}