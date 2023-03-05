package com.luv2code.springmvc.service;

import com.luv2code.springmvc.models.grades.*;
import com.luv2code.springmvc.models.students.CollegeStudent;
import com.luv2code.springmvc.models.students.GradebookCollegeStudent;
import com.luv2code.springmvc.repository.HistoryGradesDao;
import com.luv2code.springmvc.repository.MathGradesDao;
import com.luv2code.springmvc.repository.ScienceGradesDao;
import com.luv2code.springmvc.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentAndGradeService {
    private final StudentDao studentDao;
    private final StudentGrades studentGrades;
    private final MathGrade mathGrade;
    private final MathGradesDao mathGradesDao;
    private final ScienceGrade scienceGrade;
    private final ScienceGradesDao scienceGradesDao;
    private final HistoryGrade historyGrade;
    private final HistoryGradesDao historyGradesDao;

    @Autowired
    public StudentAndGradeService(StudentDao studentDao, StudentGrades studentGrades,
                                  @Qualifier("mathGrades") MathGrade mathGrade,
                                  MathGradesDao mathGradesDao,
                                  @Qualifier("scienceGrades") ScienceGrade scienceGrade,
                                  ScienceGradesDao scienceGradesDao,
                                  @Qualifier("historyGrades") HistoryGrade historyGrade,
                                  HistoryGradesDao historyGradesDao) {
        this.studentDao = studentDao;
        this.studentGrades = studentGrades;
        this.mathGrade = mathGrade;
        this.mathGradesDao = mathGradesDao;
        this.scienceGrade = scienceGrade;
        this.scienceGradesDao = scienceGradesDao;
        this.historyGrade = historyGrade;
        this.historyGradesDao = historyGradesDao;
    }

    public void createStudent(String firstname, String lastName, String emailAddress) {
        CollegeStudent student = new CollegeStudent(firstname, lastName, emailAddress);
        student.setId(0);
        studentDao.save(student);
    }

    public boolean checkIfStudentIsNull(int id) {
        Optional<CollegeStudent> student = studentDao.findById(id);
        return student.isPresent();
    }

    public void deleteStudent(int id) {
        if (checkIfStudentIsNull(id)) {
            studentDao.deleteById(id);
            mathGradesDao.deleteByStudentId(id);
            historyGradesDao.deleteByStudentId(id);
            scienceGradesDao.deleteByStudentId(id);
        }
    }

    public Iterable<CollegeStudent> getGradebook() {
        return studentDao.findAll();
    }

    public boolean createGrade(double grade, int studentId, String gradeType) {
        if (!checkIfStudentIsNull(studentId)) {
            return false;
        }
        if (grade >= 0 && grade <= 100) {
            if (gradeType.equals("math")) {
                mathGrade.setId(0);
                mathGrade.setGrade(grade);
                mathGrade.setStudentId(studentId);
                mathGradesDao.save(mathGrade);
                return true;
            }
            if (gradeType.equals("science")) {
                scienceGrade.setId(0);
                scienceGrade.setGrade(grade);
                scienceGrade.setStudentId(studentId);
                scienceGradesDao.save(scienceGrade);
                return true;
            }
            if (gradeType.equals("history")) {
                historyGrade.setId(0);
                historyGrade.setGrade(grade);
                historyGrade.setStudentId(studentId);
                historyGradesDao.save(historyGrade);
                return true;
            }
        }
        return false;
    }

    public int deleteGrade(int id, String gradeType) {
        int studentId = 0;
        if (gradeType.equals("math")) {
            Optional<MathGrade> grade = mathGradesDao.findById(id);
            if (grade.isEmpty()) {
                return studentId;
            }
            studentId = grade.get().getStudentId();
            mathGradesDao.deleteById(id);
        }
        if (gradeType.equals("science")) {
            Optional<ScienceGrade> grade = scienceGradesDao.findById(id);
            if (grade.isEmpty()) {
                return studentId;
            }
            studentId = grade.get().getStudentId();
            scienceGradesDao.deleteById(id);
        }
        if (gradeType.equals("history")) {
            Optional<HistoryGrade> grade = historyGradesDao.findById(id);
            if (grade.isEmpty()) {
                return studentId;
            }
            studentId = grade.get().getStudentId();
            historyGradesDao.deleteById(id);
        }
        return studentId;
    }

    public GradebookCollegeStudent studentInformation(int id) {
        Optional<CollegeStudent> student = studentDao.findById(id);
        if (student.isPresent()) {
            Iterable<MathGrade> mathGrades = mathGradesDao.findGradeByStudentId(id);
            Iterable<HistoryGrade> historyGrades = historyGradesDao.findGradeByStudentId(id);
            Iterable<ScienceGrade> scienceGrades = scienceGradesDao.findGradeByStudentId(id);

            List<Grade> mathGradeList = new ArrayList<>();
            mathGrades.forEach(mathGradeList::add);
            List<Grade> historyGradeList = new ArrayList<>();
            historyGrades.forEach(historyGradeList::add);
            List<Grade> scienceGradeList = new ArrayList<>();
            scienceGrades.forEach(scienceGradeList::add);

            studentGrades.setMathGradeResults(mathGradeList);
            studentGrades.setHistoryGradeResults(historyGradeList);
            studentGrades.setScienceGradeResults(scienceGradeList);

            return new GradebookCollegeStudent(student.get().getId(),
                    student.get().getFirstname(), student.get().getLastname(), student.get().getEmailAddress(),
                    studentGrades);
        }
        return null;
    }

    public void configureStudentInformationModel(int id, Model model) {
        GradebookCollegeStudent studentEntity = studentInformation(id);
        model.addAttribute("student", studentEntity);
        if (studentEntity.getStudentGrades().getMathGradeResults().size() > 0) {
            model.addAttribute("mathAverage", studentEntity.getStudentGrades().findGradePointAverage(
                    studentEntity.getStudentGrades().getMathGradeResults()
            ));
        } else {
            model.addAttribute("mathAverage", "N/A");
        }
        if (studentEntity.getStudentGrades().getScienceGradeResults().size() > 0) {
            model.addAttribute("scienceAverage", studentEntity.getStudentGrades().findGradePointAverage(
                    studentEntity.getStudentGrades().getScienceGradeResults()
            ));
        } else {
            model.addAttribute("scienceAverage", "N/A");
        }
        if (studentEntity.getStudentGrades().getHistoryGradeResults().size() > 0) {
            model.addAttribute("historyAverage", studentEntity.getStudentGrades().findGradePointAverage(
                    studentEntity.getStudentGrades().getHistoryGradeResults()
            ));
        } else {
            model.addAttribute("historyAverage", "N/A");
        }
    }
}