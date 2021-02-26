package com.jobseek.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jobseek.model.Seeker;

public class SeekerService {

    private Connection con;
    PreparedStatement pst;
    Statement stmt;
    ResultSet rs;
    String sql = null;

    public SeekerService(Connection con) throws SQLException, ClassNotFoundException {
        this.con = con;
        System.out.println("Seeker Service online and ready to go!");
    }

    public void createTable() throws SQLException {
        this.batchStatements(new String[] {
                "CREATE DATABASE IF NOT EXISTS AVENSYS",
                "USE AVENSYS",
                "CREATE TABLE IF NOT EXISTS SEEKERS " +
                        "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                        " username VARCHAR(255), " +
                        " password VARCHAR(255), " +
                        " educationLevel VARCHAR(255), " +
                        " school VARCHAR(255), " +
                        " yearGraduated INTEGER, " +
                        " PRIMARY KEY ( id ))"
        });
    }

    public void dropTable() throws SQLException {
        this.batchStatements(new String[] {
                "CREATE DATABASE IF NOT EXISTS AVENSYS",
                "USE AVENSYS",
                "DROP TABLE IF EXISTS SEEKERS"
        });
    }

    public void populateMockData() throws SQLException {
        this.batchStatements(new String[] {
                "INSERT INTO SEEKERS( username, password, educationLevel, school, yearGraduated ) VALUES"
                        + "('user1', 'pass', 'bachelors', 'Harvard', 2020 )",
                "INSERT INTO SEEKERS( username, password, educationLevel, school, yearGraduated ) VALUES"
                        + "('user2', 'pass', 'bachelors', 'NUS', 2009 )",
                "INSERT INTO SEEKERS( username, password, educationLevel, school, yearGraduated ) VALUES"
                        + "('user3', 'pass', 'masters', 'Oxford', 2000 )"
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

    public boolean insertOneRecord(Seeker seeker) throws SQLException {
        ArrayList<Seeker> seekers = this.getRecordsByUsername(seeker.getUsername());
        if(seekers.size() > 0) return false;   // SEEKER with username exists

        this.sql = "INSERT INTO SEEKERS( username, password, educationLevel, school, yearGraduated ) "
                + "VALUES(?, ?, ?, ?, ?)";
        this.pst = con.prepareStatement(sql);
        this.pst.setString(1, seeker.getUsername());
        this.pst.setString(2, seeker.getPassword());
        this.pst.setString(3, seeker.getEducationLevel());
        this.pst.setString(4, seeker.getSchool());
        this.pst.setInt(5, seeker.getYearGraduated());
        int recordsUpdated = this.pst.executeUpdate();
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        if(recordsUpdated == 0) {
            System.out.println("Not Inserted!");
            return false;
        } else {
            System.out.println("Inserted: " + seeker);
            return true;
        }
    }

    public ArrayList<Seeker> getRecordsByUsername(String username) throws SQLException {
        ArrayList<Seeker> seekers = new ArrayList<Seeker>();
        this.sql = "SELECT * FROM SEEKERS WHERE username = ?";
        this.pst = con.prepareStatement(sql);
        this.pst.setString(1, username);
        this.rs = pst.executeQuery();
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        while(this.rs.next() == true) {
            seekers.add(new Seeker(
                    this.rs.getInt("id"),
                    this.rs.getString("username"),
                    this.rs.getString("educationLevel"),
                    this.rs.getString("school"),
                    this.rs.getInt("yearGraduated")
            ));
        }
        return seekers;
    }

    public Seeker getOneRecord(Seeker seeker) throws SQLException {
        Seeker result = null;
        this.sql = "SELECT * FROM SEEKERS WHERE username=? AND password=?";
        this.pst = con.prepareStatement(sql);
        this.pst.setString(1, seeker.getUsername());
        this.pst.setString(2, seeker.getPassword());
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

    public ArrayList<Seeker> getAllRecords() throws SQLException {
        ArrayList<Seeker> seekers = new ArrayList<Seeker>();
        this.sql = "SELECT * FROM SEEKERS";
        this.stmt = con.createStatement();
        this.rs = stmt.executeQuery(this.sql);
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        while(this.rs.next() == true) {
            seekers.add(new Seeker(
                    this.rs.getInt("id"),
                    this.rs.getString("username"),
                    this.rs.getString("educationLevel"),
                    this.rs.getString("school"),
                    this.rs.getInt("yearGraduated")
            ));
        }
        return seekers;
    }
}
