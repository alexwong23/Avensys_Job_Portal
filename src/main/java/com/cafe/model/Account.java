package com.cafe.model;

public class Account {

    private String username;
    private String password;
    private String type;

    // used when retrieving records for comparison
    public Account(String username, String type) {
        this.username = username;
        this.type = type;
    }

    public Account(String username, String password, String type) {
        this.username = username;
        // todo: store password as a hash
        this.password = password;
        this.type = type;
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
    public void setType(String type) {
        this.type = type;
    }
}
