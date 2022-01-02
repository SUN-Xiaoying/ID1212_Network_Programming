package com.xiao.courseflow.service;

public class CourseNotFoundException extends Throwable{
    public CourseNotFoundException(String message) {
        super(message);
    }
}
