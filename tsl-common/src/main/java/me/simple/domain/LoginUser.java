package me.simple.domain;

/**
 * Created by chensheng on 17/6/4.
 */
public class LoginUser implements java.io.Serializable {
    private static final long serialVersionUID = 273851448896084090L;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
