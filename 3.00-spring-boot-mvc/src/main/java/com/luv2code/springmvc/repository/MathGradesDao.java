package com.luv2code.springmvc.repository;

import com.luv2code.springmvc.models.grades.MathGrade;
import jdk.jfr.Registered;
import org.springframework.data.repository.CrudRepository;

@Registered
public interface MathGradesDao extends CrudRepository<MathGrade, Integer> {
    Iterable<MathGrade> findGradeByStudentId(int id);

    void deleteByStudentId(int id);
}