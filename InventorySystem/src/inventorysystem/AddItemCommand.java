/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

import Prototype.Prototype;
import inventorysystem.Connection.SingletonConnection;
import inventorysystem.EmployeeandTransactionLog.TransactionLog;
import inventorysystem.EmployeeandTransactionLog.Employee;
import inventorysystem.ItemandFactory.ItemFactoryImpl;
import inventorysystem.ItemandFactory.ItemFactory;
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
public class AddItemCommand implements Command, Prototype{
    
    // Establishing a connection to the database using the SingletonConnection class
    private static Connection conn = SingletonConnection.getInstance().getCon();
    
    private static ItemFactory itemFactory = new ItemFactoryImpl();

    public void execute() {
            // Code for adding a new item
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                //validating itemId
                System.out.println("Enter the ID of the new item");
                String itemIdInput = reader.readLine();
                if (!isValidInteger(itemIdInput)) {
                    System.out.println("ERROR: Item ID must be a valid integer.");
                    return;
                }
                int id = Integer.parseInt(itemIdInput);
                
                if(id<=0) {
                    System.out.println("Please enter a positive number as an ID");
                    return;
                }
                
                //checking whether the entered id already exists in the database
                if (itemExistInDB(id)) {
                    System.out.println("Item with ID " + id + " already exists");
                } else {
                    System.out.println("Enter the name of the new item");
                    String name = reader.readLine();
                    //validating itemname
                    if(name.isEmpty()) {
                        System.out.println("Please enter a valid name");
                        return;
                    }
                    //validating quantity
                    System.out.println("Enter the quantity of the new item");
                    String itemquantity = reader.readLine();
                    if (!isValidInteger(itemquantity)) {
                        System.out.println("ERROR: Item ID must be a valid integer.");
                        return;
                    }
                    int quantity = Integer.parseInt(itemquantity);
                    
                    if(quantity<=0) {
                        System.out.println("Please enter a positive number as quantity");
                        return;
                    }
                    System.out.println("Enter the name of the employee responsible for the new item");
                    String empName = reader.readLine();
                    //validating employeename
                    if(empName.isEmpty()) {
                        System.out.println("Please enter a valid employee name");
                        return;
                    }
                    // Check if employee name exists in the employee database
                    Employee employee = new Employee(empName);
                    boolean employeeExists = employeeExistInDB(empName);

                    if (!employeeExists) {    ///if there is no user mentioned employee name adding it
                        // Insert employee name into the employee database
                        employee.addEmployeeToDB();
                        System.out.println("Employee added");
                    }
                    //validation for price
                    System.out.println("Enter the price of the new item");
                    String itemprice = reader.readLine();
                    if (!isValidInteger(itemprice)) {
                        System.out.println("ERROR: Item ID must be a valid integer.");
                        return;
                    }
                    float price = Integer.parseInt(itemprice);
                    
                    if(price<=0) {
                        System.out.println("Please enter a positive number as price");
                        return;
                    }
                    // Create an item object
                    Item item = itemFactory.createItem(id, name, quantity, empName, price, LocalDateTime.now()); 
                    //adding item to the database
                    itemFactory.addItemToDB(item);
                    // Create a transaction log entry
                    TransactionLog.createLogEntry("Add Item", item, price, LocalDateTime.now(), 
                            empName, name);
                    // Display the stock added
                    System.out.println("Stock Added: ");
                    System.out.println( "ID"+ "\t" + "Name"+ "\t" + "Price"+ "\t" + "Quantity"+ "\t" + "Date Added");
                    DisplayAdd(item, price);                   
                }
            } catch (IOException e) {
                // If there is an error getting input from the user, display an error message
                System.out.println("Error getting input from user: " + e.getMessage());
            }
    }
    
    // Method to validate if the input is a valid integer
    public boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
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
    
    // Method to clone the AddItemCommand object
    public Prototype clone() {
        return new AddItemCommand();
    }
    
    // The DisplayAdd method takes in an Item object and the item's price as parameters.
    private static void DisplayAdd(Item i, float itemPrice) {
        System.out.println(
        i.getID() + "\t" +
        i.getName() + "\t" +
        itemPrice + "\t" +
        i.getQuantity() + "\t" +
        i.getDateCreated());
    }
    
 
}