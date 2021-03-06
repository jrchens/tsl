package me.simple.domain;

import me.simple.validator.group.Remove;
import org.hibernate.validator.constraints.Length;

public class SysRole implements java.io.Serializable {

private int id;
private String rolename;
private String viewname;
private boolean deleted;
private boolean disabled;
private String cruser;
private java.sql.Timestamp crtime;
private String mduser;
private java.sql.Timestamp mdtime;

  public SysRole() {
  }

  public SysRole(int id) {
    this.id = id;
  }

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