package com.luv2code.springmvc.models.students;

import com.luv2code.springmvc.models.grades.StudentGrades;

public class GradebookCollegeStudent extends CollegeStudent {
    private int id;

    private final StudentGrades studentGrades;

    public GradebookCollegeStudent(int id, String firstname, String lastname, String emailAddress,
                                   StudentGrades studentGrades) {
        super(firstname, lastname, emailAddress);
        this.studentGrades = studentGrades;
        this.id = id;
    }

    public StudentGrades getStudentGrades() {
        return studentGrades;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}