package com.luv2code.springmvc.models.grades;

public interface Grade {
    int getId();
    void setId(int id);

    double getGrade();
    void setGrade(double grade);

    int getStudentId();
    void setStudentId(int studentId);
}