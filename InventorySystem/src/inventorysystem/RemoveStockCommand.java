/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

import Prototype.Prototype;
import inventorysystem.EmployeeandTransactionLog.TransactionLog;
import inventorysystem.EmployeeandTransactionLog.Employee;
import inventorysystem.Connection.SingletonConnection;
import inventorysystem.ItemandFactory.ItemDeleteFactoryImpl;
import inventorysystem.ItemandFactory.ItemDeleteFactory;
import inventorysystem.ItemandFactory.Item;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author savin
 */
public class RemoveStockCommand implements Command, Prototype {
    
    private static Connection conn = SingletonConnection.getInstance().getCon(); 
    
    //Creating an instance of the ItemDeleteFactory class
    private static ItemDeleteFactory itemDeleteFactory = new ItemDeleteFactoryImpl();

    public void execute() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Employee Name: > ");
            String empname = reader.readLine();
            //validating employeename
            if(empname.isEmpty()) {
                System.out.println("Please enter a valid employee name");
                return;
            }
    //        Connection conn = SingletonConnection.getCon();

            // Find the employee in the database
            Employee employee = new Employee(empname);

            if (!employeeExistInDB(empname)) {
                System.out.println("ERROR: Employee not found");
                return;
            }

            //Getting the item ID from the user
            //validating itemId
            System.out.println("\nItem ID");
            String itemIdInput = reader.readLine();
            if (!isValidInteger(itemIdInput)) {
                System.out.println("ERROR: Item ID must be a valid integer.");
                return;
            }
            
            int itemId = Integer.parseInt(itemIdInput);
            
            if(itemId<=0) {
                    System.out.println("Please enter a positive number as an ID");
                    return;
            }

            // Find the item in the database
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM item WHERE id = ?");
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            
            //Checking if the item exists in the database
            if (!rs.next()) {
                System.out.println("ERROR: Item not found");
                return;
            }

            System.out.println("How many items would you like to remove?");
            String Quantity = reader.readLine();
            if (!isValidInteger(Quantity)) {
                System.out.println("ERROR: Item ID must be a valid integer.");
                return;
            }
            int itemQuantity = Integer.parseInt(Quantity);
            
            if (itemQuantity > rs.getInt("quantity") || itemQuantity < 0) {
            System.out.println("ERROR: Quantity too many or below 0");
            } else {
                
                
            // Retrieve the price of the item
            float itemPrice = rs.getFloat("price");

            //create an Item object with the details of the item being removed
            Item item = itemDeleteFactory.deleteItem(itemId, itemQuantity, empname, LocalDateTime.now());

            //remove the item quantity from the item database
            itemDeleteFactory.deleteItemDB(itemId, itemQuantity, empname);

           // Log the item removal
            TransactionLog.createLogEntry("Item Removed", item, itemPrice, LocalDateTime.now(),empname, 
                    rs.getString("name"));          
           
            }
        
        } catch (SQLException e) {
            System.out.println("Error with SQL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error getting input from user: " + e.getMessage());
        }
    }
    
    // Method to validate if the input is a valid integer
    public static boolean isValidInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean employeeExistInDB(String employeeName) {
        boolean employeeExists = false;
        try {
            // SQL query to select the employee with the given name from the employee table
            String sql = "SELECT * FROM employee WHERE EmpName = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, employeeName);
            ResultSet result = stmt.executeQuery();

            // if the employee name is found in the result set, set employeeExists to true
            if (result.next()) {
                employeeExists = true;
            }
        } catch (SQLException e) {
            System.out.println("Error checking employee name in database: " + e.getMessage());
        }
        return employeeExists;
    }
    
    // Method to clone the RemoveStockCommand object
    public Prototype clone() {
        return new RemoveStockCommand();
    }

    
}


