package com.cafe.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cafe.model.Account;
import com.cafe.model.Job;

public class ApplicationService {

    private final String username = "root";
    private final String password = "password";
    Connection con = null;
    PreparedStatement pst;
    Statement stmt;
    ResultSet rs;
    String sql = null;

    public ApplicationService() throws SQLException, ClassNotFoundException {
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
                "DROP TABLE IF EXISTS APPLICATIONS",
                "CREATE TABLE APPLICATIONS " +
                        "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                        " seekerID INTEGER, " +
                        " jobID INTEGER, " +
                        " PRIMARY KEY ( id )," +
                        " FOREIGN KEY ( seekerID ) REFERENCES SEEKERS(id)," +
                        " FOREIGN KEY ( jobID ) REFERENCES JOBS(id)) "
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

    public boolean insertOneRecord(Account account, int jobID) throws SQLException {
        this.sql = "INSERT INTO APPLICATIONS( seekerID, jobID ) VALUES(?, ?)";
        this.pst = con.prepareStatement(sql);
        this.pst.setInt(1, account.getID());
        this.pst.setInt(2, jobID);
        int recordInserted = this.pst.executeUpdate();
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        if(recordInserted == 0) {
            return false;
        } else {
            return true;
        }
    }

    // for seeker to retrieve their list of applied jobs
    public ArrayList<Job> getRecordsBySeekerID(int seekerID) throws SQLException {
        ArrayList<Job> jobs = new ArrayList<>();
        this.sql = "SELECT * FROM JOBS " +
                " INNER JOIN APPLICATIONS ON JOBS.id = APPLICATIONS.jobID " +
                " WHERE APPLICATIONS.seekerID = ?";
        this.pst = con.prepareStatement(sql);
        this.pst.setInt(1, seekerID);
        this.rs = pst.executeQuery();
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        while(this.rs.next() == true) {
            jobs.add(new Job(
                    this.rs.getInt("id"),
                    this.rs.getString("company"),
                    this.rs.getString("title"),
                    this.rs.getDouble("salary"),
                    this.rs.getBoolean("isAvailable")
            ));
        }
        return jobs;
    }
}
