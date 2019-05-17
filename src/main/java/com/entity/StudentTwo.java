package com.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

public class StudentTwo {
    private Integer sid;
    private String sname;
    private Set<Course> courseSet=new HashSet<>();

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Set<Course> getCourseSet() {
        return courseSet;
    }

    public void setCourseSet(Set<Course> courseSet) {
        this.courseSet = courseSet;
    }
}
