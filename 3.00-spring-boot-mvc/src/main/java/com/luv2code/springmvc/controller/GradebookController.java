package com.luv2code.springmvc.controller;

import com.luv2code.springmvc.models.students.CollegeStudent;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GradebookController {
    private final StudentAndGradeService studentService;

    @Autowired
    public GradebookController(StudentAndGradeService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getStudents(Model model) {
        Iterable<CollegeStudent> collegeStudents = studentService.getGradebook();
        model.addAttribute("students", collegeStudents);
        return "index";
    }

    @PostMapping(value = "/")
    public String createStudent(@ModelAttribute("student") CollegeStudent student,
                                Model model) {
        studentService.createStudent(student.getFirstname(), student.getLastname(), student.getEmailAddress());
        Iterable<CollegeStudent> collegeStudents = studentService.getGradebook();
        model.addAttribute("students", collegeStudents);
        return "index";
    }

    @GetMapping("delete/student/{id}")
    public String deleteStudent(@PathVariable int id, Model model) {
        if (!studentService.checkIfStudentIsNull(id)) {
            return "error";
        }
        studentService.deleteStudent(id);
        Iterable<CollegeStudent> collegeStudents = studentService.getGradebook();
        model.addAttribute("students", collegeStudents);
        return "index";
    }

    @GetMapping("/studentInformation/{id}")
    public String studentInformation(@PathVariable int id, Model model) {
        if (!studentService.checkIfStudentIsNull(id)) {
            return "error";
        }
        studentService.configureStudentInformationModel(id, model);
        return "studentInformation";
    }

    @PostMapping("/grades")
    public String createGrade(@RequestParam("grade") double grade,
                              @RequestParam("gradeType") String gradeType,
                              @RequestParam("studentId") int studentId,
                              Model model) {
        if (!studentService.checkIfStudentIsNull(studentId)) {
            return "error";
        }
        boolean success = studentService.createGrade(grade, studentId, gradeType);
        if (!success) {
            return "error";
        }
        studentService.configureStudentInformationModel(studentId, model);
        return "studentInformation";
    }

    @GetMapping("/grades/{id}/{gradeType}")
    public String deleteGrade(@PathVariable int id,
                              @PathVariable String gradeType,
                              Model model) {
        int studentId = studentService.deleteGrade(id, gradeType);
        if (studentId == 0) {
            return "error";
        }
        studentService.configureStudentInformationModel(studentId, model);
        return "studentInformation";
    }
}