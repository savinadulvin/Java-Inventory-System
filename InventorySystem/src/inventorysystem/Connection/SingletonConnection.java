/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author savin
 */

public class SingletonConnection {
    //private static variable conn to store the connection object
    private static Connection conn;
    //private static variable instance to store the instance of the SingletonConnection class
    private static SingletonConnection instance;

    //private constructor to create the connection to the database
    private SingletonConnection() {
        try {
            conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/managetransaction", //URL of the database
            "root",  //username
            "");  //password
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    //public method to create and return an instance of the SingletonConnection class
    public static SingletonConnection getInstance() {
        if (instance == null) {
            instance = new SingletonConnection();
        }
        return instance;
    }

    //public method to return the connection object
    public Connection getCon() {
        return conn;
    }
}