package com.xiao;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;


public class Classess {
    public Classess() {
    }

    public Classess(String name) {
        this.name = name;
    }

    private String id;
    private String name;

    /*
     * 1. 声明集合类型时, 需使用接口类型, 因为 hibernate 在获取
     * 集合类型时, 返回的是 Hibernate 内置的集合类型, 而不是 JavaSE 一个标准的集合实现.
     * 2. 需要把集合进行初始化, 可以防止发生空指针异常
     */
    private Set<Student> stu =new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStu() {
        return stu;
    }

    public void setStu(Set<Student> stu) {
        this.stu = stu;
    }
}

