package com.luv2code.springmvc;

import com.luv2code.springmvc.models.grades.HistoryGrade;
import com.luv2code.springmvc.models.grades.MathGrade;
import com.luv2code.springmvc.models.grades.ScienceGrade;
import com.luv2code.springmvc.models.students.CollegeStudent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class MvcTestingExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(MvcTestingExampleApplication.class, args);
    }

    @Bean
    @Scope(value = "prototype")
    CollegeStudent getCollegeStudent() {
        return new CollegeStudent();
    }

    @Bean
    @Scope(value = "prototype")
    @Qualifier("mathGrades")
    MathGrade getMathGrade() {
        return new MathGrade();
    }

    @Bean
    @Scope(value = "prototype")
    @Qualifier("scienceGrades")
    ScienceGrade getScienceGrade() {
        return new ScienceGrade();
    }

    @Bean
    @Scope(value = "prototype")
    @Qualifier("historyGrades")
    HistoryGrade getHistoryGrade() {
        return new HistoryGrade();
    }
}