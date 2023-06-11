/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

import Prototype.Prototype;
import inventorysystem.Connection.SingletonConnection;
import inventorysystem.ItemandFactory.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author savin
 */
public class InventoryReportCommand implements Command, Prototype{
    
    private static Connection conn = SingletonConnection.getInstance().getCon();   
    
    public void execute(){
    System.out.println("\nAll items");
        System.out.println("ID" + "\t" + "Name" + "\t" + "Quantity");

        try {
            String sql = "SELECT * FROM item";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            // Looping through each item and displaying the item's ID, name, and quantity
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                int quantity = result.getInt("quantity");
                
                // Creating a new item object with the retrieved information
                Item item = new Item.Builder().ID(id).Name(name).Quantity(quantity)
                        .EmpName("Unknown").DateCreated(LocalDateTime.now()).build();
                
                // Calling the DisplayItem method to display the item's information
                DisplayItem(item);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving inventory report from database: " + e.getMessage());
        }
    }
    
    public Prototype clone() {
        return new InventoryReportCommand();
    }
    
    // Method to display the item's information
    private static void DisplayItem(Item i)
     {
    	 System.out.println(
            i.getID() + "\t" +
            i.getName() + "\t" +
            i.getQuantity());
     }
    
}
