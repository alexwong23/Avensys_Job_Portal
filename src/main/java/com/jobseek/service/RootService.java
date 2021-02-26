package com.jobseek.service;

import com.jobseek.service.ApplicationService;
import com.jobseek.service.JobService;

import java.sql.*;

public class RootService {

    private final String username = "root";
    private final String password = "password";
    Connection con = null;
    PreparedStatement pst;
    Statement stmt;
    ResultSet rs;
    String sql = null;

    private SeekerService seekerService;
    private ManagerService managerService;
    private JobService jobService;
    private ApplicationService applicationService;

    public RootService() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded successfully");
        this.con = DriverManager.getConnection("jdbc:mysql://localhost/AVENSYS", username, password);
        System.out.println("Connection to DB is established");
        con.setAutoCommit(false);        	// to allow for batch transactions

        this.seekerService = new SeekerService(con);
        this.managerService = new ManagerService(con);
        this.jobService = new JobService(con);
        this.applicationService = new ApplicationService(con);
    }

    public void closeResources() throws SQLException {
        this.rs.close();
        this.pst.close();
        this.stmt.close();
        this.con.close();
    }

    public void dropTables() throws SQLException {
        this.applicationService.dropTable();
        this.jobService.dropTable();
        this.managerService.dropTable();
        this.seekerService.dropTable();
    }

    public void createTables() throws SQLException {
        this.seekerService.createTable();
        this.managerService.createTable();
        this.jobService.createTable();
        this.applicationService.createTable();
    }

    public void populateMockData() throws SQLException {
        this.seekerService.populateMockData();
        this.managerService.populateMockData();
        this.jobService.populateMockData();
//        this.applicationService.populateMockData();
    }

    public SeekerService getSeekerService() {
        return this.seekerService;
    }

    public ManagerService getManagerService() {
        return this.managerService;
    }

    public JobService getJobService() {
        return this.jobService;
    }

    public ApplicationService getApplicationService() {
        return this.applicationService;
    }
}
