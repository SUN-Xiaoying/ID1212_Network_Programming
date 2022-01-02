package com.xiao.courseflow.model;

import javax.persistence.Table;

@Table(name="c_s_selector")
public class CSSelector {
    private int course_id;
    private int student_id;

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }
}

