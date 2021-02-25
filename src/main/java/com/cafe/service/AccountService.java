package com.cafe.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cafe.model.Account;
import com.cafe.model.Bill;

public class AccountService {

    private final String username = "root";
    private final String password = "password";
    Connection con = null;
    PreparedStatement pst;
    Statement stmt;
    ResultSet rs;
    String sql = null;

    public AccountService() throws SQLException, ClassNotFoundException {
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
                "DROP TABLE IF EXISTS ACCOUNTS",
                "CREATE TABLE ACCOUNTS " +
                        "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                        " username VARCHAR(255), " +
                        " password VARCHAR(255), " +
                        " type VARCHAR(255), " +
                        " PRIMARY KEY ( id ))"
        });
    }

    public void populateTable() throws SQLException {
        this.batchStatements(new String[] {
                "INSERT INTO ACCOUNTS( username, password, type ) VALUES"
                        + "('user1', 'password', 'jobseeker')",
                "INSERT INTO ACCOUNTS( username, password, type ) VALUES"
                        + "('user2', 'password', 'jobseeker')",
                "INSERT INTO ACCOUNTS( username, password, type ) VALUES"
                        + "('user3', 'password', 'company')"
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

    public boolean insertOneRecord(Account account) throws SQLException {
        ArrayList<Account> accounts = this.getRecordsByUsernameAndType(account.getUsername(), account.getType());
        if(accounts.size() > 0) return false;   // account with username exists

        this.sql = "INSERT INTO ACCOUNTS(username, password, type) VALUES(?, ?, ?)";
        this.pst = con.prepareStatement(sql);
        this.pst.setString(1, account.getUsername());
        this.pst.setString(2, account.getPassword());
        this.pst.setString(3, account.getType());
        int recordsUpdated = this.pst.executeUpdate();
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        if(recordsUpdated == 0) {
            System.out.println("Not Inserted!");
            return false;
        } else {
            System.out.println("Inserted: " + account);
            return true;
        }
    }

    public ArrayList<Account> getRecordsByUsernameAndType(String username, String type) throws SQLException {
        ArrayList<Account> accounts = new ArrayList<Account>();
        this.sql = "SELECT * FROM ACCOUNTS WHERE username = ? AND type = ?";
        this.pst = con.prepareStatement(sql);
        this.pst.setString(1, username);
        this.pst.setString(2, type);
        this.rs = pst.executeQuery();
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        while(this.rs.next() == true) {
            accounts.add(new Account(
                    this.rs.getString("username"),
                    this.rs.getString("type")
            ));
        }
        return accounts;
    }

    public Account getOneRecordByType(Account account) throws SQLException {
        Account result = null;
        this.sql = "SELECT * FROM ACCOUNTS WHERE username=? AND password=? AND type=?";
        this.pst = con.prepareStatement(sql);
        this.pst.setString(1, account.getUsername());
        this.pst.setString(2, account.getPassword());
        this.pst.setString(3, account.getType());
        this.rs = this.pst.executeQuery();
        this.con.commit();						// save changes
        this.con.rollback();                    // If There Is Error
        if(this.rs.next() != false) {
            result = new Account(
                this.rs.getString("username"),
                this.rs.getString("type")
            );
        }
        return result;
    }

    public ArrayList<Account> getAllRecords() throws SQLException {
        ArrayList<Account> accounts = new ArrayList<Account>();
        this.sql = "SELECT * FROM ACCOUNTS";
        this.stmt = con.createStatement();
        this.rs = stmt.executeQuery(this.sql);
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        while(this.rs.next() == true) {
            accounts.add(new Account(
                    this.rs.getString("username"),
                    this.rs.getString("type")
            ));
        }
        return accounts;
    }
}
