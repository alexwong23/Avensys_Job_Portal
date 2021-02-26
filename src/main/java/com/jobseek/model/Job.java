package com.jobseek.model;

public class Job extends Manager {
    private int jobID;
    private String title;
    private Double salary;
    private boolean isAvailable;

    // used to insert new job record
    public Job(String username, String password, String company, String industry,
               String title, Double salary) {
        super(username, password, company, industry);
        this.title = title;
        this.salary = salary;
        this.isAvailable = false;
    }

    // used to retrieve job record details
    public Job(int jobID, String username, String company, String industry,
               String title, Double salary, boolean isAvailable) {
        super(username, company, industry);
        this.jobID = jobID;
        this.title = title;
        this.salary = salary;
        this.isAvailable = isAvailable;
    }

    public int getJobID() { return this.jobID; }
    public String getTitle() { return this.title; }
    public Double getSalary() { return this.salary; }
    public boolean getAvailable() { return this.isAvailable; }

    public void setTitle(String title) { this.title = title; }
    public void setSalary(Double salary) { this.salary = salary; }
    public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }

}
