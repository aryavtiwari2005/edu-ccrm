package edu.ccrm.service;

import edu.ccrm.domain.*;
import edu.ccrm.util.exception.DuplicateEnrollmentException;
import edu.ccrm.util.exception.MaxCreditLimitExceededException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class EnrollmentService {
    
    private static final int MAX_CREDITS_PER_SEMESTER = 21;
    private final List<Enrollment> enrollments = new ArrayList<>();

    
    public void enrollStudent(Student student, Course course)
            throws DuplicateEnrollmentException, MaxCreditLimitExceededException {
        
        
        boolean alreadyEnrolled = enrollments.stream()
                .anyMatch(e -> e.getStudent().equals(student) && e.getCourse().equals(course));
        if (alreadyEnrolled) {
            throw new DuplicateEnrollmentException("Student " + student.getRegNo() + " is already enrolled in course " + course.getCode());
        }

        
        int currentCredits = student.getEnrolledCourses().stream()
                .filter(c -> c.getSemester() == course.getSemester())
                .mapToInt(Course::getCredits)
                .sum();

        if (currentCredits + course.getCredits() > MAX_CREDITS_PER_SEMESTER) {
            throw new MaxCreditLimitExceededException("Cannot enroll. Exceeds max credit limit of " +
                    MAX_CREDITS_PER_SEMESTER + " for the semester.");
        }

        
        student.enrollInCourse(course);
        enrollments.add(new Enrollment(student, course));
        System.out.println("Enrollment successful: " + student.getRegNo() + " in " + course.getCode());
    }

    
    public boolean recordGrade(Student student, Course course, Grade grade) {
        Optional<Enrollment> enrollmentOpt = enrollments.stream()
                .filter(e -> e.getStudent().equals(student) && e.getCourse().equals(course))
                .findFirst();

        if (enrollmentOpt.isPresent()) {
            enrollmentOpt.get().setGrade(grade);
            return true;
        }
        return false;
    }
    
    public List<Enrollment> getEnrollmentsForStudent(Student student) {
        return enrollments.stream()
                .filter(e -> e.getStudent().equals(student))
                .collect(Collectors.toList());
    }
}