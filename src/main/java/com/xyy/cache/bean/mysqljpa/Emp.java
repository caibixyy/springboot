package com.xyy.cache.bean.mysqljpa;

import javax.persistence.*;

@Entity
@Table(name = "emp")
public class Emp {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer e_id;
    @Column(name = "name")
    private String name;
    @Column(name = "money")
    private String money;

    private Integer dept_no;

    public Integer getE_id() {
        return e_id;
    }

    public void setE_id(Integer e_id) {
        this.e_id = e_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Integer getDept_no() {
        return dept_no;
    }

    public void setDept_no(Integer dept_no) {
        this.dept_no = dept_no;
    }

    @Override
    public String toString() {
        return "Emps{" +
                "e_id=" + e_id +
                ", name='" + name + '\'' +
                ", money='" + money + '\'' +
                ", dept_no=" + dept_no +
                '}';
    }
}
