package com.luv2code.springmvc.exceptionhandling;

public class StudentOrGradeNotFoundException extends RuntimeException {
    public StudentOrGradeNotFoundException(String message) {
        super(message);
    }
}