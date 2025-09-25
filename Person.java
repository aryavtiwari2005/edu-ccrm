package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Student extends Person {

    private String regNo;
    private StudentStatus status;
    private List<Course> enrolledCourses; 

    
    public enum StudentStatus {
        ACTIVE, INACTIVE, GRADUATED
    }

    public Student(int id, String fullName, String email, LocalDate dateOfBirth, String regNo) {
        
        super(id, fullName, email, dateOfBirth);
        this.regNo = regNo;
        this.status = StudentStatus.ACTIVE; 
        this.enrolledCourses = new ArrayList<>();
    }

    
    @Override
    public String getRole() {
        return "Student";
    }

    
    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public List<Course> getEnrolledCourses() {
        return new ArrayList<>(enrolledCourses); 
    }
    
    
    public void enrollInCourse(Course course) {
        if (course != null && !enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
        }
    }
    
    
    @Override
    public String toString() {
        return super.toString() + ", RegNo: " + regNo + ", Status: " + status;
    }
}