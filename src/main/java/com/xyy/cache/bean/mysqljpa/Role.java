package com.xyy.cache.bean.mysqljpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer rid;

    @Column(name = "name")
    private String rname;

    @ManyToMany(fetch = FetchType.LAZY)   //多对多
    @JoinTable(	//jointable维护方加此注释
            name="role_permission",		//name是表名，
//joincolumns需要将此entity中的什么字段添加到表的什么字段，name是存储在多对多关系表中的字段名，referencedColumnName为此外键
            joinColumns={@JoinColumn(name="role_id", referencedColumnName="id")},
//inverseJoinColumns,name字段是关系entity Role的id以role_id存储在关系表user_role中
            inverseJoinColumns={@JoinColumn(name="permission_id", referencedColumnName="id")})
    private Set<Permission> permissions;

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
    @JsonBackReference
    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                '}';
    }
}
