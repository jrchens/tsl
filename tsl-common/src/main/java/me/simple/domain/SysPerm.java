package me.simple.domain;

import me.simple.validator.group.Remove;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public class SysPerm implements java.io.Serializable {

private int id;
private int pid;
private String permcode;
private String viewname;
private String url;
private boolean deleted;
private boolean disabled;
private String cruser;
private java.sql.Timestamp crtime;
private String mduser;
private java.sql.Timestamp mdtime;

// ============================================================
@Length(min = 1,max = 200,groups = {Remove.class})
private String ids;
private List<SysPerm> children;
// ============================================================

  public SysPerm() {
  }

  public SysPerm(int id) {
    this.id = id;
  }

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

  public List<SysPerm> getChildren() {
    return children;
  }

  public void setChildren(List<SysPerm> children) {
    this.children = children;
  }
}