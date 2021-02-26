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

    private final String username = "root";
    private final String password = "password";
    Connection con = null;
    PreparedStatement pst;
    Statement stmt;
    ResultSet rs;
    String sql = null;

    public JobService() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded successfully");
        this.con = DriverManager.getConnection("jdbc:mysql://localhost/AVENSYS", username, password);
        System.out.println("Connection to DB is established");
        con.setAutoCommit(false);        	// to allow for batch transactions
    }

    public void closeResources() throws SQLException {
        this.rs.close();
        this.pst.close();
        this.stmt.close();
        this.con.close();
    }

    public void createTable() throws SQLException {
        this.batchStatements(new String[] {
                "CREATE DATABASE IF NOT EXISTS AVENSYS",
                "USE AVENSYS",
                "DROP TABLE IF EXISTS APPLICATIONS",  // cause of dependency
                "DROP TABLE IF EXISTS JOBS",
                "CREATE TABLE JOBS " +
                        "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                        " managerID INTEGER, " +
                        " title VARCHAR(255), " +
                        " salary DECIMAL, " +
                        " isAvailable BOOLEAN, " +
                        " PRIMARY KEY ( id ), " +
                        " FOREIGN KEY ( managerID ) REFERENCES MANAGERS(id)) "
        });
    }

    public void populateTable() throws SQLException {
        this.batchStatements(new String[] {
                "INSERT INTO JOBS( managerID, title, salary, isAvailable ) VALUES"
                        + "(1, 'banker', 4500.00, true )",
                "INSERT INTO JOBS( managerID, title, salary, isAvailable ) VALUES"
                        + "(1, 'salesman', 3300.00, false )",
                "INSERT INTO JOBS( managerID, title, salary, isAvailable ) VALUES"
                        + "(2, 'engineer', 4000.00, true )",
                "INSERT INTO JOBS( managerID, title, salary, isAvailable ) VALUES"
                        + "(3, 'project manager', 6250.00, true )",
                "INSERT INTO JOBS( managerID, title, salary, isAvailable ) VALUES"
                        + "(3, 'CEO', 25000.00, true )"
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
        this.pst.setInt(1, account.getID());
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

    // list user's jobs
    public ArrayList<Job> getRecordsByManagerID(Account account) throws SQLException {
        ArrayList<Job> jobs = new ArrayList<>();
        this.sql = "SELECT * FROM JOBS " +
                " INNER JOIN MANAGER ON JOBS.id = MANAGER.managerID " +
                " WHERE MANAGER.managerID = ?";
        this.pst = con.prepareStatement(sql);
        this.pst.setString(1, account.getUsername());
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
                " INNER JOIN MANAGER ON JOBS.id = MANAGER.managerID " +
                " WHERE MANAGER.company = ?";
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
                " INNER JOIN MANAGER ON JOBS.id = MANAGER.managerID " +
                " WHERE JOBS.isAvailable = ?";
        this.pst = con.prepareStatement(sql);
        this.pst.setBoolean(1, isAvailable);
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

    public ArrayList<Job> getAllRecords() throws SQLException {
        ArrayList<Job> jobs = new ArrayList<>();
        this.sql = "SELECT * FROM JOBS " +
                " INNER JOIN MANAGER ON JOBS.id = MANAGER.managerID ";
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
