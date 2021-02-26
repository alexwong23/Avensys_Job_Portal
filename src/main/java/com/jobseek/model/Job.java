package com.jobseek.model;

public class Job implements Company {
    private int id;
    private String company;
    private String title;
    private Double salary;
    private boolean isAvailable;

    // used to insert new job record
    public Job(String company, String title, Double salary) {
        this.company = company;
        this.title = title;
        this.salary = salary;
        this.isAvailable = false;
    }

    // used to retrieve job record details
    public Job(int id, String company, String title, Double salary, boolean isAvailable) {
        this.id = id;
        this.company = company;
        this.title = title;
        this.salary = salary;
        this.isAvailable = isAvailable;
    }

    public int getID() { return this.id; }
    public String getCompany() { return this.company; }
    public String getTitle() { return this.title; }
    public Double getSalary() { return this.salary; }
    public boolean getAvailable() { return this.isAvailable; }

    public void setCompany(String company) { this.company = company; }
    public void setTitle(String title) { this.title = title; }
    public void setSalary(Double salary) { this.salary = salary; }
    public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }

}
