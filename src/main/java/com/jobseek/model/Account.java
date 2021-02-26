package com.jobseek.model;

public class Account {

    private int accountID;
    private String username;
    private String password;
    private String type;

    // used when retrieving records for comparison
    public Account(String username) {
        this.username = username;
    }

    // used to validate login
    public Account(String username, String password) {
        this.username = username;
        // todo: store password as a hash
        this.password = password;
    }

    // used when retrieving records for comparison
    public Account(int accountID, String username, String type) {
        this.accountID = accountID;
        this.username = username;
        this.type = type;
    }

    // used for inserting records
    public Account(String username, String password, String type) {
        this.username = username;
        // todo: store password as a hash
        this.password = password;
        this.type = type;
    }

    public int getAccountID() {
        return this.accountID;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String getType() {
        return this.type;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
