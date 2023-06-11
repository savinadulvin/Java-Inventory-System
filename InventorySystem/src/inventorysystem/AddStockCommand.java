/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

import Prototype.Prototype;
import inventorysystem.EmployeeandTransactionLog.TransactionLog;
import inventorysystem.EmployeeandTransactionLog.Employee;
import inventorysystem.Connection.SingletonConnection;
import inventorysystem.ItemandFactory.ItemUpdateFactoryImpl;
import inventorysystem.ItemandFactory.ItemUpdateFactory;
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
public class AddStockCommand implements Command, Prototype{
    
    // Establishing a connection to the database using the SingletonConnection class
    private static Connection conn = SingletonConnection.getInstance().getCon();
    
    // Creating an instance of the ItemUpdateFactory to use its methods
    private static ItemUpdateFactory updateItemFactory = new ItemUpdateFactoryImpl();

    public void execute() {
            // Creating a BufferedReader to read input from the user
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                //validating itemId
                System.out.println("Enter the ID of the item to be updated");
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
//                int itemId = Integer.parseInt(reader.readLine());
                System.out.println("Enter the updated price of the item");
                String itemprice = reader.readLine();
                if (!isValidInteger(itemprice)) {
                    System.out.println("ERROR: Item ID must be a valid integer.");
                    return;
                }
                float price = Integer.parseInt(itemprice);
//                float price = Float.parseFloat(reader.readLine());
                System.out.println("Enter the name of the employee responsible for the update");
                String employeeName = reader.readLine();
                System.out.println("How many items would you like to add?");
                String itemquantity = reader.readLine();
                if (!isValidInteger(itemquantity)) {
                    System.out.println("ERROR: Item ID must be a valid integer.");
                    return;
                }
                int quantity = Integer.parseInt(itemquantity);
//                int quantity = Integer.parseInt(reader.readLine());
                
                if (quantity < 0 || employeeName.isEmpty()) {
                    System.out.println("ERROR: Quantity being added is below 0 or Employee name is empty");
                    return;
                }
                
                // Check if employee name exists in the employee database
                Employee employee = new Employee(employeeName);
                boolean employeeExists = employeeExistInDB(employeeName);

                if (!employeeExists) {    ///if there is no user mentioned employee name adding it
                    // Insert employee name into the employee database
                    employee.addEmployeeToDB();
                    System.out.println("Employee added");
                }
                try {
                    // Find the item in the database by its ID
                    String sql = "SELECT * FROM item WHERE ID = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, itemId);
                    ResultSet result = stmt.executeQuery();

                    if (!result.next()) {
                        System.out.println("ERROR: Item not found");
                        return;
                    }

                    if (!itemExistInDB(itemId)) {
                        System.out.println("ERROR: Item not found");
                        return;
                    }

                    // Retrieve the current quantity of the item from the database
                    int currentQuantity = result.getInt("quantity");
                    String itemName = result.getString("name");

                    // Update the item's quantity
                    currentQuantity += quantity;
                    System.out.println(quantity + " items have been added to Item ID: " + itemId + " on " + LocalDateTime.now());

                    // Create an updated item object with the new quantity and price
                    Item item = updateItemFactory.updateQuantityandPriceItem(itemId, currentQuantity, 
                            price, employeeName, LocalDateTime.now());


                    // Create a log entry for the update
                    TransactionLog.createLogEntry("Stock Updated", item, price, LocalDateTime.now(), 
                            employeeName, itemName);
                    // Update the item in the database
                    updateItemFactory.updateItem(itemId, currentQuantity, price, employeeName);

                } catch (SQLException e) {
                    System.out.println("Error updating record in database: " + e.getMessage());
                }
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
    
    // method to check if an item already exists in the database
    public static boolean itemExistInDB(int id) {
        try {
            
            String query = "SELECT * FROM item WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error checking if item exists in the database: " + e.getMessage());
        }
        return false;
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
    
    // Method to clone the AddStockCommand object
    public Prototype clone() {
        return new AddStockCommand();
    }
      
}
