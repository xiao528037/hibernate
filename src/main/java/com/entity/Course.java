package com.entity;


import java.util.HashSet;
import java.util.Set;


public class Course {
    private int cid;
    private String cname;
    private Set<StudentTwo> studentSet = new HashSet<>();

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Set<StudentTwo> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<StudentTwo> studentSet) {
        this.studentSet = studentSet;
    }
}
