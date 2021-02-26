package com.jobseek.model;

import com.jobseek.model.Account;
import com.jobseek.model.Company;

// TODO: extends account and implements company
public class Manager extends Account implements Company {

    private String company;
    private String industry;

    // used when validating login
    public Manager(String username, String password) {
        super(username, password);
    }

    // used when retrieving records for comparison
    public Manager(String username, String company, String industry) {
        super(username);
        this.company = company;
        this.industry = industry;
    }

    // used when retrieving records for comparison
    public Manager(int id, String username, String company, String industry) {
        super(id, username, "manager");
        this.company = company;
        this.industry = industry;
    }

    // used to create new Manager records
    public Manager(String username, String password, String company, String industry) {
        super(username, password, "manager");        // automatically change type of new manager objects
        this.company = company;
        this.industry = industry;
    }

    public String getCompany() {
        return this.company;
    }
    public String getIndustry() {
        return this.industry;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
