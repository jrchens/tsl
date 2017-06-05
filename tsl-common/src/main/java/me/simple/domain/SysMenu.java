package me.simple.domain;

import me.simple.validator.group.Get;
import org.hibernate.validator.constraints.Range;

public class SysMenu extends Base {

  @Range(min = 1,max = Integer.MAX_VALUE,groups = {Get.class})
  private int id;
private int pid;
private String viewname;
private String url;

public int getId(){
  return id;
}
public void setId(int id){
  this.id = id;
}
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