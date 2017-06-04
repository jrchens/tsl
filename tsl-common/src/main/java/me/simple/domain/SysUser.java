package me.simple.domain;

import me.simple.validator.group.Edit;
import me.simple.validator.group.Remove;
import me.simple.validator.group.Save;
import me.simple.validator.group.Update;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class SysUser extends Base {
    @Range(min=1,max=Integer.MAX_VALUE,groups = {Edit.class, Update.class, Remove.class})
    private int id;

    @Length(min = 4, max = 32, groups = {Save.class, Update.class})
    private String username;

    @Length(min = 2, max = 32, groups = {Save.class, Update.class})
    private String viewname;

    @Length(min = 4, max = 32, groups = {Save.class})
    private String password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getViewname() {
        return viewname;
    }

    public void setViewname(String viewname) {
        this.viewname = viewname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}