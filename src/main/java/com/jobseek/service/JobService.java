package com.jobseek.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jobseek.model.Account;
import com.jobseek.model.Job;

public class JobService {

    private Connection con;
    PreparedStatement pst;
    Statement stmt;
    ResultSet rs;
    String sql = null;

    public JobService(Connection con) throws SQLException, ClassNotFoundException {
        this.con = con;
        System.out.println("Job Service online and ready to go!");
    }

    public void createTable() throws SQLException {
        this.batchStatements(new String[] {
                "CREATE DATABASE IF NOT EXISTS AVENSYS",
                "USE AVENSYS",
                "CREATE TABLE IF NOT EXISTS JOBS " +
                        "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                        " managerID INTEGER, " +
                        " title VARCHAR(255), " +
                        " salary DECIMAL, " +
                        " isAvailable BOOLEAN, " +
                        " PRIMARY KEY ( id ), " +
                        " FOREIGN KEY ( managerID ) REFERENCES MANAGERS(id)) "
        });
    }

    public void dropTable() throws SQLException {
        this.batchStatements(new String[] {
                "CREATE DATABASE IF NOT EXISTS AVENSYS",
                "USE AVENSYS",
                "DROP TABLE IF EXISTS JOBS"
        });
    }

    public void populateMockData() throws SQLException {
        this.batchStatements(new String[] {
                "INSERT INTO JOBS( managerID, title, salary, isAvailable ) VALUES"
                        + "(1, 'Project Manager', 5000.00, true )",
                "INSERT INTO JOBS( managerID, title, salary, isAvailable ) VALUES"
                        + "(1, 'Technology Consultant', 3500.00, false )",
                "INSERT INTO JOBS( managerID, title, salary, isAvailable ) VALUES"
                        + "(2, 'Digital Marketer', 4000.00, true )",
                "INSERT INTO JOBS( managerID, title, salary, isAvailable ) VALUES"
                        + "(3, 'Project Lead', 4000.00, true )",
                "INSERT INTO JOBS( managerID, title, salary, isAvailable ) VALUES"
                        + "(3, 'CEO', 20000.00, true )"
        });
    }

    public void batchStatements(String[] sqlStatements) throws SQLException {
        this.stmt = con.createStatement();
        for(String s: sqlStatements) {
            stmt.addBatch(s);				// add statements to batch
        }
        stmt.executeBatch();				// add execute batch
        con.commit();						// save changes
        con.rollback();     				// If There Is Error
    }

    public boolean insertOneRecord(Account account, Job job) throws SQLException {
        this.sql = "INSERT INTO JOBS( managerID, title, salary, boolean ) VALUES(?, ?, ?, ?)";
        this.pst = con.prepareStatement(sql);
        this.pst.setInt(1, account.getAccountID());
        this.pst.setString(2, job.getTitle());
        this.pst.setDouble(3, job.getSalary());
        this.pst.setBoolean(4, job.getAvailable());
        int recordsUpdated = this.pst.executeUpdate();
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        if(recordsUpdated == 0) {
            System.out.println("Not Inserted!");
            return false;
        } else {
            System.out.println("Inserted: " + job);
            return true;
        }
    }

    public Job getOneRecordByID(int jobID) throws SQLException {
        Job job = null;
        this.sql = "SELECT * FROM JOBS " +
                " INNER JOIN MANAGERS ON JOBS.managerID = MANAGERS.id " +
                " WHERE JOBS.id = ?";
        this.pst = con.prepareStatement(sql);
        this.pst.setInt(1, jobID);
        this.rs = pst.executeQuery();
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        if(this.rs.next() != false) {
            job = new Job(
                this.rs.getInt("id"),
                this.rs.getString("username"),
                this.rs.getString("company"),
                this.rs.getString("industry"),
                this.rs.getString("title"),
                this.rs.getDouble("salary"),
                this.rs.getBoolean("isAvailable")
            );
        }
        return job;
    }

    // list account's jobs
    public ArrayList<Job> getRecordsByAccount(Account account) throws SQLException {
        if(account.getType().equals("seeker")) {
            // show jobs applied by seeker
            this.sql = "SELECT * FROM JOBS " +
                    " INNER JOIN MANAGERS ON JOBS.managerID = MANAGERS.id " +
                    " INNER JOIN APPLICATIONS ON JOBS.id = APPLICATIONS.jobID " +
                    " INNER JOIN SEEKERS ON SEEKERS.id = APPLICATIONS.seekerID " +
                    " WHERE APPLICATIONS.seekerID = ?";
        } else if(account.getType().equals("manager")) {
            // show jobs posted by manager
            this.sql = "SELECT * FROM JOBS " +
                    " INNER JOIN MANAGERS ON JOBS.managerID = MANAGERS.id " +
                    " WHERE MANAGERS.id = ?";
        }
        ArrayList<Job> jobs = new ArrayList<>();
        this.pst = con.prepareStatement(sql);
        this.pst.setInt(1, account.getAccountID());
        this.rs = pst.executeQuery();
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        while(this.rs.next() == true) {
            jobs.add(new Job(
                    this.rs.getInt("id"),
                    this.rs.getString("username"),
                    this.rs.getString("company"),
                    this.rs.getString("industry"),
                    this.rs.getString("title"),
                    this.rs.getDouble("salary"),
                    this.rs.getBoolean("isAvailable")
            ));
        }
        return jobs;
    }

    // for company to retrieve their list of jobs
    public ArrayList<Job> getRecordsByCompany(String company) throws SQLException {
        ArrayList<Job> jobs = new ArrayList<>();
        this.sql = "SELECT * FROM JOBS " +
                " INNER JOIN MANAGERS ON JOBS.managerID = MANAGERS.id " +
                " WHERE MANAGERS.company = ?";
        this.pst = con.prepareStatement(sql);
        this.pst.setString(1, company);
        this.rs = pst.executeQuery();
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        while(this.rs.next() == true) {
            jobs.add(new Job(
                    this.rs.getInt("id"),
                    this.rs.getString("username"),
                    this.rs.getString("company"),
                    this.rs.getString("industry"),
                    this.rs.getString("title"),
                    this.rs.getDouble("salary"),
                    this.rs.getBoolean("isAvailable")
            ));
        }
        return jobs;
    }

    // list available jobs to job seekers
    public ArrayList<Job> getRecordsByAvailability(boolean isAvailable) throws SQLException {
        ArrayList<Job> jobs = new ArrayList<>();
        this.sql = "SELECT * FROM JOBS " +
                " INNER JOIN MANAGERS ON JOBS.managerID = MANAGERS.id " +
                " WHERE JOBS.isAvailable = ?";
        this.pst = con.prepareStatement(sql);
        this.pst.setBoolean(1, isAvailable);
        this.rs = pst.executeQuery();
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        while(this.rs.next() == true) {
            jobs.add(new Job(
                    this.rs.getInt("JOBS.id"),
                    this.rs.getString("username"),
                    this.rs.getString("company"),
                    this.rs.getString("industry"),
                    this.rs.getString("title"),
                    this.rs.getDouble("salary"),
                    this.rs.getBoolean("isAvailable")
            ));
        }
        return jobs;
    }

    public ArrayList<Job> getAllRecords() throws SQLException {
        ArrayList<Job> jobs = new ArrayList<>();
        this.sql = "SELECT * FROM JOBS " +
                " INNER JOIN MANAGERS ON JOBS.managerID = MANAGERS.id ";
        this.stmt = con.createStatement();
        this.rs = stmt.executeQuery(this.sql);
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        while(this.rs.next() == true) {
            jobs.add(new Job(
                    this.rs.getInt("id"),
                    this.rs.getString("username"),
                    this.rs.getString("company"),
                    this.rs.getString("industry"),
                    this.rs.getString("title"),
                    this.rs.getDouble("salary"),
                    this.rs.getBoolean("isAvailable")
            ));
        }
        return jobs;
    }

}
