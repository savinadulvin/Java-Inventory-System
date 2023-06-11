/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;


import Prototype.Prototype;
import inventorysystem.Connection.SingletonConnection;
import inventorysystem.EmployeeandTransactionLog.Employee;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 *
 * @author savin
 */
public class ReportPersonalUsageCommand implements Command, Prototype{
    
    private static Connection conn = SingletonConnection.getInstance().getCon(); 
    
    public void execute(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        try{
            System.out.println("Enter employee name: ");
            String empname = reader.readLine();

            // Check if employee name exists in the employee database
            Employee employee = new Employee(empname);
            boolean employeeExists = employeeExistInDB(empname);

            if (!employeeExists) {    ///if there is no user mentioned employee name exits the program
                System.out.println("You given Employee is not found");
                return;
            }

            try {

                // Retrieve the "Item Removed" transactions for the specified employee from the transactionlog table
                String sql = "SELECT * FROM transactionlog WHERE Type = 'Item Removed' AND EmployeeName = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, empname);
                ResultSet result = stmt.executeQuery();

                if (!result.next() ){
                    System.out.println("No items taken from the stock");
                    return;
                }

                System.out.println("Date Taken" + "\t" + "ID" + "\t" + "Name" + "\t" + "Quantity");

                while (result.next()) {

                    // Retrieve the date, ID, name, and quantity of the item
                    Timestamp dateTaken = result.getTimestamp("datecreated");
                    int itemId = result.getInt("id");
                    String itemName = result.getString("name");
                    int itemQuantity = result.getInt("quantity");

                    LocalDateTime ldt = dateTaken.toLocalDateTime();     //to convert Timestamp object to LocalDateTime.

                    DisplayPersonalUsage(ldt, itemId, itemName, itemQuantity);
                }

            } catch (SQLException e) {
                System.out.println("Error retrieving data from employee and transactionlog tables: " + e.getMessage());
            }
        } catch (IOException e){
            System.out.println("Error getting input from the user "+ e.getMessage());
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
   
    // Method to clone the ReportPersonalUsageCommand object
    public Prototype clone() {
        return new ReportPersonalUsageCommand();
    }
    
    public static void DisplayPersonalUsage(LocalDateTime date, int id, String name, int quantity)
     {
    	 System.out.println(date + "\t" + id + "\t" + name + "\t" + quantity);
     }

}
