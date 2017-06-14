package me.simple.domain;

import me.simple.validator.group.Remove;
import org.hibernate.validator.constraints.Length;

public class SysUserGroup implements java.io.Serializable {

private int id;
private int uid;
private int gid;
private int srt;
private boolean deleted;
private boolean disabled;
private String cruser;
private java.sql.Timestamp crtime;
private String mduser;
private java.sql.Timestamp mdtime;

// ============================================================
@Length(min = 1,max = 200,groups = {Remove.class})
private String ids;
// ============================================================

public int getId(){
  return id;
}
public void setId(int id){
  this.id = id;
}
public int getUid(){
  return uid;
}
public void setUid(int uid){
  this.uid = uid;
}
public int getGid(){
  return gid;
}
public void setGid(int gid){
  this.gid = gid;
}
public int getSrt(){
  return srt;
}
public void setSrt(int srt){
  this.srt = srt;
}
public boolean getDeleted(){
  return deleted;
}
public void setDeleted(boolean deleted){
  this.deleted = deleted;
}
public boolean getDisabled(){
  return disabled;
}
public void setDisabled(boolean disabled){
  this.disabled = disabled;
}
public String getCruser(){
  return cruser;
}
public void setCruser(String cruser){
  this.cruser = cruser;
}
public java.sql.Timestamp getCrtime(){
  return crtime;
}
public void setCrtime(java.sql.Timestamp crtime){
  this.crtime = crtime;
}
public String getMduser(){
  return mduser;
}
public void setMduser(String mduser){
  this.mduser = mduser;
}
public java.sql.Timestamp getMdtime(){
  return mdtime;
}
public void setMdtime(java.sql.Timestamp mdtime){
  this.mdtime = mdtime;
}

public String getIds() {
  return ids;
}

public void setIds(String ids) {
  this.ids = ids;
}

}