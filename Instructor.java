package edu.ccrm.domain;

import java.time.LocalDate;


public class Instructor extends Person {

    private String department;
    private String title; 

    public Instructor(int id, String fullName, String email, LocalDate dateOfBirth, String department, String title) {
        
        super(id, fullName, email, dateOfBirth);
        this.department = department;
        this.title = title;
    }

    
    @Override
    public String getRole() {
        return "Instructor";
    }

    
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return super.toString() + ", Title: " + getTitle() + ", Department: " + department;
    }
}