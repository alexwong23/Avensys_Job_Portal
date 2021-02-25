package com.cafe.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cafe.model.Item;

public class FoodService {

    private final String username = "root";
    private final String password = "password";
    Connection con = null;
    PreparedStatement pst;
    Statement stmt;
    ResultSet rs;
    String sql = null;

    public FoodService() throws SQLException, ClassNotFoundException {
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
                "DROP TABLE IF EXISTS ITEMS",
                "CREATE TABLE ITEMS " +
                        "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                        " name VARCHAR(255), " +
                        " price INTEGER, " +
                        " PRIMARY KEY ( id ))"
        });
    }

    public void populateTable() throws SQLException {
        this.batchStatements(new String[] {
                "INSERT INTO ITEMS( name, price ) VALUES"
                        + "('Cheesecake', 9.0)",
                "INSERT INTO ITEMS( name, price ) VALUES"
                        + "('Latte', 4.5)",
                "INSERT INTO ITEMS( name, price ) VALUES"
                        + "('Eggs', 2.0)"
        });
    }

    public void insertOneRecord(Item item) throws SQLException {
        this.sql = "INSERT INTO ITEMS(name, price) VALUES(?, ?)";
        this.pst = con.prepareStatement(sql);
        this.pst.setString(1, item.getName());
        this.pst.setDouble(2, item.getPrice());
        int recordsUpdated = this.pst.executeUpdate();
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        if(recordsUpdated == 0) {
            System.out.println("Not Inserted!");
        } else {
            System.out.println("Inserted: " + item);
        }
    }

    public void updateRecordsByName(double newPrice, String itemName) throws SQLException {
        this.sql = "UPDATE ITEMS SET price = ? WHERE name = ?";
        this.pst = con.prepareStatement(sql);
        this.pst.setDouble(1, newPrice);
        this.pst.setString(2, itemName);
        int recordsUpdated = this.pst.executeUpdate();
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        if(recordsUpdated == 0) {
            System.out.println("Not Updated!");
        } else {
            System.out.println("Updated: " + itemName);
        }
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

    public ArrayList<Item> getRecordsByName(String itemName) throws SQLException {
        ArrayList<Item> items = new ArrayList<Item>();
        this.sql = "SELECT * FROM ITEMS WHERE name = ?";
        this.pst = con.prepareStatement(sql);
        this.pst.setString(1, itemName);
        this.rs = pst.executeQuery();
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        while(this.rs.next() == true) {
            items.add(new Item(
                    this.rs.getString("name"),
                    this.rs.getDouble("price")
            ));
        }
        return items;
    }

    public ArrayList<Item> getAllRecords() throws SQLException {
        ArrayList<Item> items = new ArrayList<Item>();
        this.sql = "SELECT * FROM ITEMS";
        this.stmt = con.createStatement();
        this.rs = stmt.executeQuery(this.sql);
        this.con.commit();						// save changes
        this.con.rollback();     				// If There Is Error
        while(this.rs.next() == true) {
            items.add(new Item(
                    this.rs.getString("name"),
                    this.rs.getDouble("price")
            ));
        }
        return items;
    }
}
