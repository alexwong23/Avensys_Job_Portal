package com.jobseek.service;

import com.jobseek.model.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagerService {

    private Connection con;
    PreparedStatement pst;
    Statement stmt;
    ResultSet rs;
    String sql = null;

    public ManagerService(Connection con) throws SQLException, ClassNotFoundException {
        this.con = con;
        System.out.println("Manager Service online and ready to go!");
    }

    public void createTable() throws SQLException {
        this.batchStatements(new String[] {
                "CREATE DATABASE IF NOT EXISTS AVENSYS",
                "USE AVENSYS",
                "CREATE TABLE IF NOT EXISTS MANAGERS " +
                        "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                        " username VARCHAR(255), " +
                        " password VARCHAR(255), " +
                        " company VARCHAR(255), " +
                        " industry VARCHAR(255), " +
                        " PRIMARY KEY ( id ))"
        });
    }

    public void dropTable() throws SQLException {
        this.batchStatements(new String[] {
                "CREATE DATABASE IF NOT EXISTS AVENSYS",
                "USE AVENSYS",
                "DROP TABLE IF EXISTS MANAGERS"
        });
    }

    public void populateMockData() throws SQLException {
        this.batchStatements(new String[] {
                "INSERT INTO MANAGERS( username, password, company, industry ) VALUES"
                        + "('mang1', 'pass', 'Accenture', 'IT Consulting' )",
                "INSERT INTO MANAGERS( username, password, company, industry ) VALUES"
                        + "('mang2', 'pass', 'FaceBook', 'Marketing' )",
                "INSERT INTO MANAGERS( username, password, company, industry ) VALUES"
                        + "('mang3', 'pass', 'Accenture', 'IT Consulting' )"
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

    public boolean insertOneRecord(Manager manager) throws SQLException {
        ArrayList<Manager> managers = this.getRecordsByUsername(manager.getUsername());
        if(managers.size() > 0) return false;   // SEEKER with username exists

        this.sql = "INSERT INTO MANAGERS( username, password, company, industry ) "
                + "VALUES(?, ?, ?, ?)";
        this.pst = con.prepareStatement(sql);
        this.pst.setString(1, manager.getUsername());
        this.pst.setString(2, manager.getPassword());
        this.pst.setString(3, manager.getCompany());
        this.pst.setString(5, manager.getIndustry());
        int recordsUpdated = this.pst.executeUpdate();
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        if(recordsUpdated == 0) {
            System.out.println("Not Inserted!");
            return false;
        } else {
            System.out.println("Inserted: " + manager);
            return true;
        }
    }

    public ArrayList<Manager> getRecordsByUsername(String username) throws SQLException {
        ArrayList<Manager> managers = new ArrayList<>();
        this.sql = "SELECT * FROM MANAGERS WHERE username = ?";
        this.pst = con.prepareStatement(sql);
        this.pst.setString(1, username);
        this.rs = pst.executeQuery();
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        while(this.rs.next() == true) {
            managers.add(new Manager(
                    this.rs.getInt("id"),
                    this.rs.getString("username"),
                    this.rs.getString("company"),
                    this.rs.getString("industry")
            ));
        }
        return managers;
    }

    public Manager getOneRecord(Manager manager) throws SQLException {
        Manager result = null;
        this.sql = "SELECT * FROM MANAGERS WHERE username=? AND password=?";
        this.pst = con.prepareStatement(sql);
        this.pst.setString(1, manager.getUsername());
        this.pst.setString(2, manager.getPassword());
        this.rs = this.pst.executeQuery();
        this.con.commit();						// save changes
        this.con.rollback();                    // If There Is Error
        if(this.rs.next() != false) {
            result = new Manager(
                    this.rs.getInt("id"),
                    this.rs.getString("username"),
                    this.rs.getString("company"),
                    this.rs.getString("industry")
            );
        }
        return result;
    }

    public ArrayList<Manager> getAllRecords() throws SQLException {
        ArrayList<Manager> managers = new ArrayList<>();
        this.sql = "SELECT * FROM MANAGERS";
        this.stmt = con.createStatement();
        this.rs = stmt.executeQuery(this.sql);
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        while(this.rs.next() == true) {
            managers.add(new Manager(
                    this.rs.getInt("id"),
                    this.rs.getString("username"),
                    this.rs.getString("company"),
                    this.rs.getString("industry")
            ));
        }
        return managers;
    }
}
