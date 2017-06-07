package me.simple.domain;

public class SysGroup extends Base {

private int id;
private String groupname;
private String viewname;

public int getId(){
  return id;
}
public void setId(int id){
  this.id = id;
}
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