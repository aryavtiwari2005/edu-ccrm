package edu.ccrm.domain;


public class Enrollment {
    private final Student student;
    private final Course course;
    private Grade grade;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.grade = Grade.NOT_GRADED; 
    }

    
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public Grade getGrade() { return grade; }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("Enrollment[Student: %s, Course: %s, Grade: %s]",
                student.getRegNo(), course.getCode(), grade);
    }
}