package edu.ccrm.service;

import edu.ccrm.domain.Student;
import edu.ccrm.domain.Student.StudentStatus;

import java.util.*;


public class StudentService {

    private final Map<String, Student> students = new LinkedHashMap<>();

    public boolean addStudent(Student student) {
        if (students.containsKey(student.getRegNo())) {
            System.out.println("Error: Student with registration number " + student.getRegNo() + " already exists.");
            return false;
        }
        students.put(student.getRegNo(), student);
        return true;
    }

    public Optional<Student> findStudentByRegNo(String regNo) {
        return Optional.ofNullable(students.get(regNo));
    }

    public boolean deactivateStudent(String regNo) {
        Optional<Student> studentOpt = findStudentByRegNo(regNo);
        if (studentOpt.isPresent()) {
            studentOpt.get().setStatus(StudentStatus.INACTIVE);
            return true;
        }
        return false;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }
}