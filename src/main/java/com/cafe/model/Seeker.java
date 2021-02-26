package com.cafe.model;

public class Seeker extends Account {

    private String educationLevel;
    private String school;
    private int yearGraduated;

    // used when validating login
    public Seeker(String username, String password) {
        super(username, password);
    }

    // used when retrieving records for comparison
    public Seeker(int id, String username, String educationLevel, String school, int yearGraduated) {
        super(id, username, "seeker");
        this.educationLevel = educationLevel;
        this.school = school;
        this.yearGraduated = yearGraduated;
    }

    // used to create new Seeker records
    public Seeker(String username, String password, String educationLevel, String school, int yearGraduated) {
        super(username, password, "seeker");        // automatically change type of new seeker objects
        this.educationLevel = educationLevel;
        this.school = school;
        this.yearGraduated = yearGraduated;
    }

    public String getEducationLevel() {
        return this.educationLevel;
    }
    public String getSchool() {
        return this.school;
    }
    public int getYearGraduated() {
        return this.yearGraduated;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public void setYearGraduated(int yearGraduated) {
        this.yearGraduated = yearGraduated;
    }
}
