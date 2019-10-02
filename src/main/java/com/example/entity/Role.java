package com.example.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="boot_role")
public class Role implements Serializable {

    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 8719878517384639149L;

    @Id
    @Column(name="id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String loginName ;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;

    public Long getId() {
        return id;
    }


    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Role(Long id, String name, Date createdate) {
        super();
        this.id = id;
        this.loginName  = name;
        this.createdate = createdate;
    }

    public Role() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedate() {
        return createdate;
    }
}