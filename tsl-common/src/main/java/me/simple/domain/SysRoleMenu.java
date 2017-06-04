package me.simple.domain;

public class SysRoleMenu extends Base {

private int role_id;
private int menu_id;
private int srt;

public int getRole_id(){
  return role_id;
}
public void setRole_id(int role_id){
  this.role_id = role_id;
}
public int getMenu_id(){
  return menu_id;
}
public void setMenu_id(int menu_id){
  this.menu_id = menu_id;
}
public int getSrt(){
  return srt;
}
public void setSrt(int srt){
  this.srt = srt;
}
}