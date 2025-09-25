package edu.ccrm.domain;

import java.time.LocalDate;


public abstract class Person {
    private int id;
    private String fullName;
    private String email;
    private final LocalDate dateOfBirth; 

    
    protected Person(int id, String fullName, String email, LocalDate dateOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth; 
    }

    
    public abstract String getRole();

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + fullName + ", Email: " + email;
    }
}