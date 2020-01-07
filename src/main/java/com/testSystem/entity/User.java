package com.testSystem.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_all_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "passwd", nullable = false)
    private String passwd;

    @Column(name = "authority", nullable = false)
    private int authority;

    @Column(name = "gentime", nullable = false)
    private Date gentime;

    @Column(name = "modtime")
    private Date modtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public Date getGentime() {
        return gentime;
    }

    public void setGentime(Date gentime) {
        this.gentime = gentime;
    }

    public Date getModtime() {
        return modtime;
    }

    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }
}
