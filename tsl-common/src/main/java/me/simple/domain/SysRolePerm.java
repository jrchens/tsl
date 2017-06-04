package me.simple.domain;

public class SysRolePerm extends Base {

private int role_id;
private int perm_id;
private String permcode;
private int srt;

public int getRole_id(){
  return role_id;
}
public void setRole_id(int role_id){
  this.role_id = role_id;
}
public int getPerm_id(){
  return perm_id;
}
public void setPerm_id(int perm_id){
  this.perm_id = perm_id;
}
public String getPermcode(){
  return permcode;
}
public void setPermcode(String permcode){
  this.permcode = permcode;
}
public int getSrt(){
  return srt;
}
public void setSrt(int srt){
  this.srt = srt;
}
}