package com.luv2code.springmvc.models;

import org.springframework.stereotype.Component;

@Component
public interface Grade {
    double getGrade();
    int getId();
    void setId(int id);
    int getStudentId();
    void setStudentId(int studentId);
    void setGrade(double grade);
}