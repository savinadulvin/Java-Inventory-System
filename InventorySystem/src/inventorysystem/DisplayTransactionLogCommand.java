/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

import Prototype.Prototype;
import inventorysystem.EmployeeandTransactionLog.TransactionLog;
import inventorysystem.Connection.SingletonConnection;
import inventorysystem.ItemandFactory.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author savin
 */
public class DisplayTransactionLogCommand implements Command, Prototype{
    private static Connection conn = SingletonConnection.getInstance().getCon();   
    
    public void execute(){
        // transaction log
        System.out.println("\nTransaction Log:");
        System.out.println("Date"+ "\t" + "Type"+ "\t" + "ID"+ "\t" + "Name"+ "\t" + "Employee"+ "\t" + "Price");
//        Connection conn = SingletonConnection.getCon();
        
        try {
            // Retrieving all records from the transactionlog table
            String sql = "SELECT * FROM transactionlog";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                // Parsing the date from the database into a LocalDateTime object
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime date = LocalDateTime.parse(result.getString("datecreated"),formatter);
                String type = result.getString("type");
                int id = result.getInt("id");
                String name = result.getString("name");
                String employeeName = result.getString("employeeName");
                float price = result.getFloat("price");
                int quantity = result.getInt("quantity");
                
                // Creating a new Item object using the data retrieved from the database
                Item item = new Item.Builder().ID(id).Name(name).Quantity(quantity)
                    .EmpName(employeeName).DateCreated(LocalDateTime.now()).build();
                
                // Creating a new TransactionLog object using the data retrieved from the database
                TransactionLog tl = new TransactionLog(type, item, price, date,employeeName, name);
                
                // Calling the DisplayTransactions method to display the transaction log to the user
                DisplayTransactions(tl);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving transaction log from database: " + e.getMessage());
        }
    }
    
    // Method to clone the DisplayTransactionLogCommand object
    public Prototype clone() {
        return new DisplayTransactionLogCommand();
    }
    
    public static void DisplayTransactions(TransactionLog tl)
     {
         
         System.out.println(
        tl.DateAdded + "\t" +
        tl.TypeOfTransaction + "\t" +
        tl.ItemID + "\t" +
        tl.ItemName + "\t" +
        tl.EmployeeName + "\t" +
        (tl.TypeOfTransaction.equals("Item Removed") ? "" : "" + tl.ItemPrice));
     }
    
}
