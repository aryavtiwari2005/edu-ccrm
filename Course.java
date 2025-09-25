package edu.ccrm.domain;

public final class Course {

    private final String code;
    private final String title;
    private final int credits;
    private final Instructor instructor;
    private final Semester semester;
    private final String department;

    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.instructor = builder.instructor;
        this.semester = builder.semester;
        this.department = builder.department;
    }

    public static class Builder {
        private final String code;
        private final String title;
        private int credits = 3;
        private Instructor instructor = null;
        private Semester semester = Semester.FALL;
        private String department = "General";

        public Builder(String code, String title) {
            this.code = code;
            this.title = title;
        }

        public Builder credits(int credits) {
            this.credits = credits;
            return this;
        }

        public Builder instructor(Instructor instructor) {
            this.instructor = instructor;
            return this;
        }

        public Builder semester(Semester semester) {
            this.semester = semester;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

        
        public Course build() {
            return new Course(this);
        }
    }

    
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public Instructor getInstructor() { return instructor; }
    public Semester getSemester() { return semester; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        String instructorName = (instructor != null) ? instructor.getFullName() : "Not Assigned";
        return String.format("Course[Code: %s, Title: %s, Credits: %d, Department: %s, Instructor: %s]",
                code, title, credits, department, instructorName);
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return code.equals(course.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}