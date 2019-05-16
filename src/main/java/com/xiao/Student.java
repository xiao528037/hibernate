package com.xiao;

import lombok.Data;


public class Student {
    private String id;
    private String sname;

    public Student(String sname) {
        this.sname = sname;
    }

    private Classess classess;

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Classess getClassess() {
        return classess;
    }

    public void setClassess(Classess classess) {
        this.classess = classess;
    }
}
