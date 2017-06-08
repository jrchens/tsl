package me.simple.domain;

import me.simple.validator.group.Get;
import org.hibernate.validator.constraints.Range;

import java.sql.Timestamp;

public class SysMenu implements java.io.Serializable {

  @Range(min = 1,max = Integer.MAX_VALUE,groups = {Get.class})
  private int id;
private int pid;
private String viewname;
private String url;
  private boolean disabled;
  private boolean deleted;
  private String cruser;
  private Timestamp crtime;
  private String mduser;
  private Timestamp mdtime;

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

  public boolean isDisabled() {
    return disabled;
  }

  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public String getCruser() {
    return cruser;
  }

  public void setCruser(String cruser) {
    this.cruser = cruser;
  }

  public Timestamp getCrtime() {
    return crtime;
  }

  public void setCrtime(Timestamp crtime) {
    this.crtime = crtime;
  }

  public String getMduser() {
    return mduser;
  }

  public void setMduser(String mduser) {
    this.mduser = mduser;
  }

  public Timestamp getMdtime() {
    return mdtime;
  }

  public void setMdtime(Timestamp mdtime) {
    this.mdtime = mdtime;
  }
}