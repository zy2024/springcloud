package com.example.study.entiy;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String  userName;
    private String pwd;
    private String salt;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }

    public User(String id, String userName, String pwd, String salt) {
        this.id = id;
        this.userName = userName;
        this.pwd = pwd;
        this.salt = salt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
