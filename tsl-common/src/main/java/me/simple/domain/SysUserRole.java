package me.simple.domain;

public class SysUserRole extends Base {

private int user_id;
private int role_id;
private int srt;

public int getUser_id(){
  return user_id;
}
public void setUser_id(int user_id){
  this.user_id = user_id;
}
public int getRole_id(){
  return role_id;
}
public void setRole_id(int role_id){
  this.role_id = role_id;
}
public int getSrt(){
  return srt;
}
public void setSrt(int srt){
  this.srt = srt;
}
}