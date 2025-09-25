package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Semester;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


public class CourseService {

    
    private final Map<String, Course> courses = new LinkedHashMap<>();

    
    public boolean addCourse(Course course) {
        if (courses.containsKey(course.getCode())) {
            System.out.println("Error: Course with code " + course.getCode() + " already exists.");
            return false;
        }
        courses.put(course.getCode(), course);
        return true;
    }

    
    public Optional<Course> findCourseByCode(String courseCode) {
        return Optional.ofNullable(courses.get(courseCode));
    }

    
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }

    
    public List<Course> searchCourses(Instructor instructor, String department, Semester semester) {
        
        return courses.values().stream()
                
                .filter(course -> instructor == null || instructor.equals(course.getInstructor()))
                
                .filter(course -> department == null || department.equalsIgnoreCase(course.getDepartment()))
                
                .filter(course -> semester == null || semester.equals(course.getSemester()))
                
                .collect(Collectors.toList());
    }
}