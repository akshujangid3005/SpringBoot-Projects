package com.thinkconstructive.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="user_info")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userid;
    private String username;
    private  String userage;
    private String useraddress;
    private String userphone;

    public User() {
    }

    public User(int userid, String username, String userage, String useraddress, String userphone) {
        this.userid = userid;
        this.username = username;
        this.userage = userage;
        this.useraddress = useraddress;
        this.userphone = userphone;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserage() {
        return userage;
    }

    public void setUserage(String userage) {
        this.userage = userage;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }
}
