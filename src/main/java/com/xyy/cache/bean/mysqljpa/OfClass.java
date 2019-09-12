package com.xyy.cache.bean.mysqljpa;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_class")
public class OfClass {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "class_name")
    private String className;

    //级联保存、更新、删除、刷新;延迟加载。当删除用户，会级联删除该用户的所有文章
    //拥有mappedBy注解的实体类为关系被维护端
    //mappedBy="students"中的author是Article中的author属性
    @OneToMany(mappedBy = "ofClass",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Student>  students;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "OfClass{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", students=" + students +
                '}';
    }
}
