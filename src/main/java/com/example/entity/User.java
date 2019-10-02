package com.example.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="boot_user")
public class User implements Serializable {

    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = -6550777752269466791L;

    @Id
    @Column(name="id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=50,nullable=false)
    private String name;

    private String loginName;

    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;

    @ManyToOne
    @JoinColumn(name = "did")
    private Department department;
    /**
     * 在使用此@JoinTable标记时，需要注意以下几个问题。
     * 该标记与@Table注释类似，用于标注用于关联的表。可以标注在方法或者属性上
     * name属性为连接两个表的表名称。若不指定，则使用默认的表名称如下所示。
     * “表名1”+“_”+“表名2”。
     *
     * joinColumns属性表示，在保存关系中的表中，所保存关联关系的外键的字段。并配合@JoinColumn标记使用。
     * 例如以下的映射配置，表示字段customer_id为外键关联到customer表中的id字段。
     *
     * joinColumns={
     *          @JoinColumn(name="customer_id",referencedColumnName="id")
     * }
     *inverseJoinColumns属性与joinColumns属性类似，它保存的是保存关系的另一个外键字段。
     * 例如以下的映射配置，表示字段address_id为外键关联到address表中的id字段。
     * inverseJoinColumns={
     *          @JoinColumn(name="address_id",referencedColumnName="id")
     * }
     *
     * 提示：@JoinTable不仅能够定义一对多的关联，也可以定义多对多表的关联
     * 在单向关系中没有mappedBy,主控方相当于拥有指向另一方的外键的一方。
     * 1.一对一和多对一的@JoinColumn注解的都是在“主控方”，都是本表指向外表的外键名称。
     * 2.一对多的@JoinColumn注解在“被控方”，即一的一方，指的是外表中指向本表的外键名称。
     * 3.多对多中，joinColumns写的都是本表在中间表的外键名称，
     *   inverseJoinColumns写的是另一个表在中间表的外键名称。
     **/
    @ManyToMany(cascade={},fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="roles_id")})
    private List<Role> roleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(Long id, String name, String loginName, String password,
                Date createdate, Department department, List<Role> roleList) {
        super();
        this.id = id;
        this.name = name;
        this.loginName = loginName;
        this.password = password;
        this.createdate = createdate;
        this.department = department;
        this.roleList = roleList;
    }


}