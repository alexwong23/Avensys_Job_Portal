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
import com.jobseek.model.Seeker;

public class ApplicationService {

    private Connection con;
    PreparedStatement pst;
    Statement stmt;
    ResultSet rs;
    String sql = null;

    public ApplicationService(Connection con) throws SQLException, ClassNotFoundException {
        this.con = con;
        System.out.println("Application Service online and ready to go!");
    }

    public void createTable() throws SQLException {
        this.batchStatements(new String[] {
                "CREATE DATABASE IF NOT EXISTS AVENSYS",
                "USE AVENSYS",
                "CREATE TABLE IF NOT EXISTS APPLICATIONS " +
                        "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                        " seekerID INTEGER, " +
                        " jobID INTEGER, " +
                        " accepted BOOLEAN, " +
                        " PRIMARY KEY ( id )," +
                        " FOREIGN KEY ( seekerID ) REFERENCES SEEKERS(id)," +
                        " FOREIGN KEY ( jobID ) REFERENCES JOBS(id)) "
        });
    }

    public void dropTable() throws SQLException {
        this.batchStatements(new String[] {
                "CREATE DATABASE IF NOT EXISTS AVENSYS",
                "USE AVENSYS",
                "DROP TABLE IF EXISTS APPLICATIONS"
        });
    }

    public void populateMockData() throws SQLException {
        this.batchStatements(new String[] {
                "INSERT INTO APPLICATIONS( seekerID, jobID, accepted ) VALUES ( 1, 1, FALSE )",
                "INSERT INTO APPLICATIONS( seekerID, jobID, accepted ) VALUES ( 2, 1, FALSE )",
                "INSERT INTO APPLICATIONS( seekerID, jobID, accepted ) VALUES ( 3, 1, FALSE )"
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
        this.sql = "INSERT INTO APPLICATIONS( seekerID, jobID, accepted ) VALUES(?, ?, FALSE)";
        this.pst = con.prepareStatement(sql);
        this.pst.setInt(1, account.getAccountID());
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

    public boolean updateOneRecordBySeekerJobID(int seekerID, int jobID, boolean accepted) throws SQLException {
        // update all others to false
        if(accepted) {
            this.updateRecordsByJobIDToAcceptFalse(jobID);
        }

        this.sql = "UPDATE APPLICATIONS SET accepted = ? WHERE seekerID = ? AND jobID = ?";
        this.pst = con.prepareStatement(sql);
        this.pst.setBoolean(1, accepted);
        this.pst.setInt(2, seekerID);
        this.pst.setInt(3, jobID);
        int recordInserted = this.pst.executeUpdate();
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        if(recordInserted == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void updateRecordsByJobIDToAcceptFalse(int jobID) throws SQLException {
        this.batchStatements(new String[] {
                "UPDATE APPLICATIONS SET accepted = FALSE WHERE jobID = " + jobID,
                "UPDATE JOBS SET isAvailable = FALSE WHERE id = " + jobID
        });
    }

    public Seeker getOneRecordsByJobIDAcceptTrue(int jobID) throws SQLException {
        Seeker result = null;
        this.sql = "SELECT * FROM SEEKERS " +
                " INNER JOIN APPLICATIONS ON SEEKERS.id = APPLICATIONS.seekerID " +
                " WHERE APPLICATIONS.jobID = ? AND APPLICATIONS.accepted > 0";
        this.pst = con.prepareStatement(sql);
        this.pst.setInt(1, jobID);
        this.rs = this.pst.executeQuery();
        this.con.commit();						// save changes
        this.con.rollback();                    // If There Is Error
        if(this.rs.next() != false) {
            result = new Seeker(
                    this.rs.getInt("id"),
                    this.rs.getString("username"),
                    this.rs.getString("educationLevel"),
                    this.rs.getString("school"),
                    this.rs.getInt("yearGraduated")
            );
        }
        return result;
    }
}
